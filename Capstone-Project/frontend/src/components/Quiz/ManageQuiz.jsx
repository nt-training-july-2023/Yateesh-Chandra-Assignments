import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import "./Quizzes.css";

import AdminNavBar from "../AdminNavBar";
import UserNavBar from "../UserNavBar";
import Swal from "sweetalert2";

const ManageQuiz = () => {
  const { categoryId } = useParams();
  const [quizzes, setQuizzes] = useState([]);
  const navigate = useNavigate();
  const userRole = localStorage.getItem("role");

  useEffect(() => {
    fetchQuizzes();
  }, []);

  const fetchQuizzes = () => {
    try {
      axios
        .get(`http://localhost:8082/api/v1/quiz/byCategory/${categoryId}`)
        .then((response) => {
          console.log('categoryId:', categoryId);
          setQuizzes(response?.data);
        });
    } catch (error) {
      console.error("Error fetching quizzes : ", error);
    }
  };

  const handleDeleteQuiz = (quizId) => {
        axios.delete(`http://localhost:8082/api/v1/quiz/${quizId}`)
        .then(() => {
            console.log("Quiz deleted : ", quizId);
            fetchQuizzes();
        })
        .catch((error) => console.log("Error deleting Quiz", error));
  }

  const handleEditClick = (quizId) => {
        navigate(`/add-quiz/${quizId}`);
  };

  const handleAddClick = (categoryId) => {
    console.log('categoryId before passing : ', categoryId);
    navigate('/add-quiz', { state: { categoryId: categoryId.toString() } });
  };

  const handleTakeQuizClick = (quizId, timeInMin, quizName, categoryId) => {
    navigate(`/test/${quizId}`, {state : {timeInMin, quizName, categoryId}});
  }

  return (
    <div>
        {userRole === "ADMIN" ? <AdminNavBar /> : <UserNavBar/>}
    <div className="manage-quiz-container">
      
      <div className="quiz-list-container">
        <h1>{quizzes.length === 0 ? "No Quiz Available" : "Quizzes Available"}</h1>
        {userRole === "ADMIN" && (
        <button
          className="add-category-button"
          onClick={() => handleAddClick(categoryId)}
        >
          Add Quiz
        </button>
        )}
        <div className="quiz-list-scroll-container">
          <div className="quiz-grid">
            {quizzes.map((quiz) => (
              <div className="quiz-card" key={quiz.quizId}>
                <h2>{quiz.quizName}</h2>
                <p>{quiz.quizDescription}</p>
                <p>Total No of Questions : <b>{quiz.numOfQuestions}</b></p>
                <p>Duration : <b>{quiz.timeInMin} min</b></p>
                <div className="open-quiz">
                  {userRole === "ADMIN" && (
                  <button className="edit-button" onClick={() => handleEditClick(quiz.quizId)}>Edit</button>
                  )}
                  {userRole === "ADMIN" &&(
                  <button className="delete-button" onClick ={() => 
                    Swal.fire({
                      title : "Delete Quiz?",
                      text : "Are you sure You want to delete. It cannot be undone",
                      icon : "warning",
                      showCancelButton : true,
                      cancelButtonText : "No",
                      showConfirmButton : true,
                      confirmButtonText : "Delete"
                    }).then((result) => {
                      if(result.isConfirmed){
                        handleDeleteQuiz(quiz.quizId)
                      }
                    })
                    } >Delete</button>
                  )}
                  {userRole === "ADMIN" && (
                  <button className="open-button" onClick={() => navigate(`/manage-question/${quiz.quizId}`)}>Open</button>
                  )}
                  {userRole === "USER" && (
                    <button className="open-button" onClick={() => handleTakeQuizClick(quiz.quizId, quiz.timeInMin, quiz.quizName, categoryId)}>Take Quiz</button>
                  )}
                </div>
              </div>
          ))}
        </div>
        </div>
      </div>
      </div>
    </div>
  );
};

export default ManageQuiz;