import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import AdminNavBar from "../AdminNavBar";
import "../Categories/Categories.css";
import axios from "axios";
import {useLocation} from 'react-router-dom'
import Swal from "sweetalert2";
import NotFound from "../NotFound";
import QuizService from "../../services/QuizService";
import SweetAlert from "../SweetAlerts/SweetAlert";

const AddOrUpdateQuiz = () => {
    const { quizId } = useParams();
    const navigate = useNavigate();
    const location = useLocation();
    const categoryId = location.state?.categoryId;
    const isUpdating = quizId !== undefined;
    const userRole = localStorage.getItem("role");
    const [quizName, setQuizName] = useState("");
    const [quizDescription, setQuizDescription] = useState("");
    const [numOfQuestions, setNumOfQuestions] = useState(0);
    const [timeInMin, setTimeInMin] = useState(0);
    const [quizNameError, setQuizNameError] = useState("");
    const [quizDescriptionError, setQuizDescriptionError] = useState("");
    const [numOfQuestionsError, setNumOfQuestionsError] = useState("");
    const [timeInMinError, setTimeInMinError] = useState("");

    const cancelButton = () => {
        console.log("categoryId during Cancel : ", categoryId);
        navigate(`/manage-quiz/${categoryId}`);
    };

    const fetchQuizData = () => {
        QuizService.getQuizById(quizId)
        .then((response) => {
            const { quizName, quizDescription, numOfQuestions, timeInMin} = response.data;
            setQuizName(quizName);
            setQuizDescription(quizDescription);
            setNumOfQuestions(numOfQuestions);
            setTimeInMin(timeInMin);
        })
        .catch((error) => {
            console.error("Error fetching Quiz Data: ", error);
        });
    };

    const handleQuizNameChange = (e) => {
        const validName = e.target.value;
        setQuizName(validName);
        if (!validName) {
            setQuizNameError("Quiz Name is required");
        } else {
            setQuizNameError("");
        }
    };

    const handleQuizDescriptionChange = (e) => {
      const validDescription = e.target.value;
      setQuizDescription(validDescription);
      if (!validDescription) {
        setQuizDescriptionError("Add some Description");
      } else {
        setQuizDescriptionError("");
      }
    };

    const handleQuizNumOfQuestions = (e) => {
        const validNumOfQuestions = parseInt(e.target.value, 10);
        setNumOfQuestions(validNumOfQuestions);
        if (isNaN(validNumOfQuestions) || validNumOfQuestions <= 0) {
            setNumOfQuestionsError("Enter a valid number of Questions");
        } else {
            setNumOfQuestionsError("");
        }
    };

    const handleQuizDuration = (e) => {
        const validTimeInMin = parseInt(e.target.value, 10);
        setTimeInMin(validTimeInMin);
        if (isNaN(validTimeInMin)) {
            setTimeInMinError("Enter a valid number for Duration of Minutes");
        } else if(validTimeInMin < 0){
            setTimeInMinError("Duration of minutes can not be negative");
        }
        else {
            setNumOfQuestionsError("");
        }
    };

    const validForm = () => {
        let isValid = true;
        if (!quizName) {
            setQuizNameError("Quiz Name is required");
            isValid = false;
        } else {
            setQuizNameError("");
        }

        if (!quizDescription) {
            setQuizDescriptionError("Add some Description");
            isValid = false;
        } else {
            setQuizDescriptionError("");
        }

        if(!numOfQuestions){
            setNumOfQuestionsError("Enter Number greater than 0");
        }
        else if (isNaN(numOfQuestions) || numOfQuestions <= 0) {
            setNumOfQuestionsError("Enter a valid number of Questions");
            isValid = false;
        } else {
            setNumOfQuestionsError("");
        }

        if(!timeInMin){
            setTimeInMinError("Enter Number greater than 0");
        }
        else if (isNaN(timeInMin)){
            setTimeInMinError("Enter a valid number for Duration of Minutes");
            isValid = false;
        } else if (timeInMin < 0){
            setTimeInMinError("Duration of minutes cannot be negative")
            isValid = false;
        } else {
            setTimeInMinError("");
        }

        return isValid;
    };

    const handleAddOrUpdateQuiz = async (e) => {
        e.preventDefault();
        if (!validForm()) {
            SweetAlert.missingField();
            return;
        }

        const quizData = {
            quizName,
            quizDescription,
            numOfQuestions,
            timeInMin,
            categoryId,
        };

        try {
            
            if (isUpdating) {
            
                try{
                    const res = await QuizService.updateQuiz(quizId, quizData);
                    
                    if(res?.status === 200){
                        SweetAlert.updateSuccessAlert();
                        navigate(`/manage-quiz/${categoryId}`);
                        console.log("Quiz Updated Successfully");
                        fetchQuizData();
                    }

                } catch(error) {
                        
                    if(error?.response?.data?.code === 302){
                        setQuizNameError("Quiz already exists");
                        Swal.fire({
                            title : "Quiz Already Exists!",
                            text : "Change the Quiz name",
                            icon : "warning"
                        })
                
                    } else if(error?.response?.data?.message === "Number cannot be 0 or less"){
                        Swal.fire({
                            title : "Incorrect Values Detected",
                            text : "Enter Valid Numbers",
                            icon : "error"
                        })
                    
                    }
                }

            } else {
            
                try{
                    const response = await QuizService.addQuiz(quizData);            
                
                    if(response?.status === 201){
                        SweetAlert.addSuccessAlert();
                        navigate(`/manage-quiz/${categoryId}`);
                        console.log("New Quiz is added successfully.", quizData);
                    }

                } catch(error) {
                    console.log(error);
                    if(error?.response?.data?.code === 302 ){
                        setQuizNameError("Quiz already exists");
                        Swal.fire({
                            title : "Quiz Already Exists!",
                            text : "Change the Quiz name",
                            icon : "warning"
                        })
                    } 
                    
                    else if(error?.response?.data?.message === "Number cannot be 0 or less"){
                        Swal.fire({
                            title : "Incorrect Values Detected",
                            text : "Enter Valid Numbers",
                            icon : "error"
                        })
                    }
                }
            }

        } 
        
        catch (error) {
            console.error("Update", error);
        }

    };

    useEffect(() => {
        if(isUpdating){
            fetchQuizData();
        }
    },[]);

    return (
        <div className="App">
            {userRole === "ADMIN" || userRole === "USER" ? (
                <>
            <AdminNavBar />
            <div className="add-quiz-container">
                <h1>{isUpdating ? 'Update Quiz' : 'Add Quiz'}</h1>
                <form onSubmit={handleAddOrUpdateQuiz}>
                    <div className="form-group">
                        <label>Quiz Name:</label>
                        <input
                        type="text"
                        value={quizName}
                        onChange={handleQuizNameChange}
                        placeholder="Enter Quiz Name"
                        />

                        {quizNameError && <div className="error">{quizNameError}</div>}

                        <label>Quiz Description:</label>
                        <textarea
                        value={quizDescription}
                        onChange={handleQuizDescriptionChange}
                        placeholder="Enter Quiz Description"
                        />

                        {quizDescriptionError && <div className="error">{quizDescriptionError}</div>}

                        <label>Number of Questions:</label>
                        <input
                        type="number"
                        value={numOfQuestions}
                        onChange={handleQuizNumOfQuestions}
                        placeholder="Enter Number of Questions"
                        />

                        {numOfQuestionsError && <div className="error">{numOfQuestionsError}</div>}

                        <label>Duration(in Minutes): </label>
                        <input
                        type = "number"
                        value = {timeInMin}
                        onChange = {handleQuizDuration}
                        place Holder = "Enter Duration"
                        />
                        
                        {timeInMinError && <div className="error">{timeInMinError}</div>}

                    </div>
                    
                    <div className="form-group">
                        <div className="button-container-category">
                            <button type="submit">{isUpdating ? 'Update' : 'Add'}</button>
                            <button type="button" className="red-button" onClick={cancelButton}>Cancel</button>
                        </div>
                    </div>
                </form>
            </div>
            </>
            ):(
                <div>
                    <NotFound/>
                </div>
            )}
        </div>
    );
};

export default AddOrUpdateQuiz;
