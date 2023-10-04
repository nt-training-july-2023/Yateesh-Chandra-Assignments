import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import "./Quizzes.css";
import AdminNavBar from '../../components/NavBars/AdminNavBar';
import UserNavBar from '../../components/NavBars/UserNavBar';
import { FaBackward, FaExternalLinkAlt, FaPen, FaPlusCircle, FaTasks, FaTrash } from "react-icons/fa";
import QuizService from "../../services/QuizService";
import NotFound from '../../pages/HomePage/NotFound';
import SweetAlert from '../../components/SweetAlerts/SweetAlert';
import Header1 from "../../components/Header1";
import IconButton from "../../components/IconButton";
import IconLeftButton from "../../components/IconLeftButton";

const ManageQuiz = () => {
    const { categoryId } = useParams();
    const [searchQuery, setSearchQuery] = useState('');
    const [quizzes, setQuizzes] = useState([]);
    const navigate = useNavigate();
    const userRole = localStorage.getItem("role");
    const categoryName = localStorage.getItem("categoryName")

    useEffect(() => {
        fetchQuizzes();
    }, []);

    const fetchQuizzes = () => {
        QuizService.getQuizByCategoryId(categoryId)
          .then((response) => {
              console.log('categoryId:', categoryId);
              setQuizzes(response?.data?.body);
          });
    };

    const deleteButton = (quizId) => {
        SweetAlert.deleteAlert("Quiz", quizId, handleDeleteQuiz);
    }

    const handleDeleteQuiz = (quizId) => {
        QuizService.deleteQuiz(quizId)
        .then(() => {
            console.log("Quiz deleted : ", quizId);
            fetchQuizzes();
        })
        .catch((error) => console.log("Error deleting Quiz", error));
    };

    const handleEditClick = (quizId) => {
        navigate(`/add-quiz/${quizId}`,{ state: { categoryId } });
    };

    const handleAddClick = (categoryId) => {
        console.log('categoryId before passing : ', categoryId);
        navigate('/add-quiz', { state: { categoryId: categoryId.toString() } });
    };

    const handleTakeQuizClick = (quizId, timeInMin, quizName, categoryId) => {
        navigate(`/test/${quizId}`, {state : {timeInMin, quizName, categoryId}});
    }

    const filterQuiz = quizzes.filter((quiz) =>{
        return quiz.quizName.toLowerCase().includes(searchQuery.toLowerCase());
    });

    const backButtonClick = () => {
        if(userRole === "ADMIN"){
            navigate("/manage-category");
        }
        else{
            navigate("/user");
        }
    }

    return (
        <div>
            {userRole === "ADMIN" || userRole === "USER" ? (            
            <div>
                {(userRole === "ADMIN" && (
                    <AdminNavBar />
                ))} 

                {(userRole === "USER" && (
                    <UserNavBar/>
                ))}

                <div className="manage-quiz-container">
                    <div className="quiz-list-container">
                    <Header1 className="arial" text={quizzes.length === 0 ? `No Quiz Available in "${categoryName}"` : `Quizzes Available in "${categoryName}"`}
/>
                        <div className="button-search-container">
                            <div className="left-buttons">
                            <IconLeftButton className='red-button button-big' onClick={backButtonClick} icon={<FaBackward className="small-icon" />} text="Back" />
                                {userRole === "ADMIN" && (
                                <IconButton
                                className="blue-button button-big"
                                onClick={() => handleAddClick(categoryId)}
                                icon = {<FaPlusCircle className="small-icon" />}
                                text= "Add Quiz"
                                />
                                )}
                            </div>

                            <div className = 'search-bar'>
                                <input
                                type="text"
                                className="search-input"
                                placeholder="Search Quiz"
                                value = {searchQuery}
                                onChange={(e) => setSearchQuery(e.target.value)}
                                />
                            </div>
                        </div>
            
                        <div className="quiz-list-scroll-container">
                            <div className="quiz-grid">
                                {filterQuiz.map((quiz) => (
                                <div className="quiz-card" key={quiz.quizId}>
                                    <h2>{quiz.quizName}</h2>
                                    <p>{quiz.quizDescription}</p>
                                    {userRole === "ADMIN" && (<p>Max No of Questions : <b>{quiz.numOfQuestions}</b></p>)}
                                    <p>Duration : <b>{quiz.timeInMin} min</b></p>
                                    <div className="open-quiz-button">
                                        {userRole === "ADMIN" && (
                                        <IconLeftButton className = "edit-button button-small" onClick = {() => handleEditClick(quiz.quizId)} icon={<FaPen className="very-small-icon" /> } text = "Edit"/>
                                        )}
                                        {userRole === "ADMIN" &&(
                                        <IconLeftButton className = "delete-button button-small" onClick = {() => deleteButton(quiz.quizId)} icon={<FaTrash className="very-small-icon" /> } text = "Delete"/>
                                        )}
                                        {userRole === "ADMIN" && (
                                        <IconLeftButton className = "open-button button-small" onClick={() => navigate(`/manage-question/${quiz.quizId}`, {state : {categoryId : categoryId.toString(), numOfQuestions: quiz.numOfQuestions, quizName : quiz.quizName}})} icon={<FaExternalLinkAlt className="very-small-icon" /> } text = "Open"/>
                                        )}
                                    </div>
                                    <div className="user-open-quiz-button">
                                        {userRole === "USER" && (
                                        <IconLeftButton className = "open-button button-wide" onClick={() => handleTakeQuizClick(quiz.quizId, quiz.timeInMin, quiz.quizName, categoryId)} icon={<FaTasks className="very-small-icon" /> } text = "Take Quiz"/>
                                        )}
                                    </div>
                                </div>
                                ))}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            ):(
                <div>
                    <NotFound/>
                </div>
            )}
      </div>
    );
};

export default ManageQuiz;