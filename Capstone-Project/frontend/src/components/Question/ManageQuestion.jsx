import React, { useEffect, useState } from "react";
import { useLocation, useNavigate, useParams } from "react-router-dom";
import "./ManageQuestion.css";
import AdminNavBar from "../AdminNavBar";
import { FaBackward, FaPlusCircle } from "react-icons/fa";
import NotFound from "../NotFound";
import QuestionService from "../../services/QuestionService";
import SweetAlert from "../SweetAlerts/SweetAlert";

const ManageQuestion = () => {
    const [questions, setQuestions] = useState([]);
    const location = useLocation();
    const userRole = localStorage.getItem("role");
    const categoryId = location.state?.categoryId;
    const quizName = location.state?.quizName;
    const numOfQuestions = location.state ? location.state.numOfQuestions : null;
    const { quizId } = useParams();
    const navigate = useNavigate();
    const [isAddingQuestion, setIsAddingQuestion] = useState(false);
    const [isEditingQuestion, setIsEditingQuestion] = useState(false);
    const [editedQuestion, setEditedQuestion] = useState({});
    const [newQuestion, setNewQuestion] = useState({
        questionTitle: "",
        option1: "",
        option2: "",
        option3: "",
        option4: "",
        correctOption: "",
        quizId,
    });

    useEffect(() => {
        fetchQuestions();
        console.log("Number of Questions passed : ", numOfQuestions);
        console.log("Questions.length : ", questions.length);
    }, []);

    const fetchQuestions = () => {
        QuestionService.getQuestionsByQuizId(quizId)
        .then((response) => {
            setQuestions(response.data);
        })
        .catch((error) => {
            console.error("Error fetching Questions : ", error);
        });
    };

    const backButton = () => {
        console.log("Category Id while back button : ", categoryId);
        navigate(`/manage-quiz/${categoryId}`);
    }

    const handleInputChange = (event) => {
        const { name, value } = event.target;
        if (isEditingQuestion) {
            setEditedQuestion({ ...editedQuestion, [name]: value });
        } else {
           setNewQuestion({ ...newQuestion, [name]: value });
        }
    };

    const handleAddQuestion = () => {
        if(!newQuestion.questionTitle || !newQuestion.option1 || !newQuestion.option2 || !newQuestion.option3
            || !newQuestion.option4 || !newQuestion.correctOption){
                SweetAlert.missingField();
                return;
        }

        QuestionService.addQuestion(newQuestion)
        .then(() => {
            SweetAlert.successAlert("Added");
            fetchQuestions();
            setNewQuestion({
                questionTitle: "",
                option1: "",
                option2: "",
                option3: "",
                option4: "",
                correctOption: "",
                quizId,
            });
            console.log("Question added successfully");
            setIsAddingQuestion(false);
        })
        .catch((error) => {
            console.log("Error adding question", error);
            if(error?.response?.data?.message === "Options should not be repeated"){
                SweetAlert.duplicateOptions();
            }
        });

    };

    const handleEditQuestion = () => {
        if(!editedQuestion.questionTitle || !editedQuestion.option1 || !editedQuestion.option2 || !editedQuestion.option3
            || !editedQuestion.option4 || !editedQuestion.correctOption){
                SweetAlert.missingField();
                return;
        }
        QuestionService.updateQuestion(editedQuestion.questionId, editedQuestion)
        .then(() => {
            SweetAlert.successAlert("Updated");
            fetchQuestions();
            setIsEditingQuestion(false);
            console.log("Question updated successfully");
        })
        .catch((error) => {
            console.error("Error updating question", error);
            if(error?.response?.data?.message === "Options should not be repeated"){
                SweetAlert.duplicateOptions();
            }
        });
    };

    const handleDeleteQuestion = (questionId) => {
        QuestionService.deleteQuestion(questionId)
        .then(() => {
            console.log("Question deleted: ", questionId);
            fetchQuestions();
        })
        .catch((error) => console.error("Error deleting Questions : ", error.response));
    };

    const handleDeleteButton = (questionId) => {
        SweetAlert.deleteAlert("Question", questionId, handleDeleteQuestion)
    }

    const handleEditClick = (question) => {
        setEditedQuestion({ ...question });
        setIsEditingQuestion(true);
    };

    return (
        <div className="App">
            {userRole === "ADMIN" ? (
            <>
            <AdminNavBar/>
            <div className="manage-questions-container">
                <h1>{!(isAddingQuestion || isEditingQuestion) ? (questions.length === 0 ? `No Questions Available - ${quizName}` :`Manage Questions - ${quizName} `) : (isAddingQuestion ? 'Add New Question' : 'Edit Question')}</h1>
                {!isAddingQuestion && !isEditingQuestion ? (
                <div className="button-container">
                    <button className="red-button" onClick={backButton}>
                        <FaBackward className="small-icon" /> Back
                    </button>
                   
                    <button
                    className="blue-button"
                    onClick={questions.length < numOfQuestions ? (() => setIsAddingQuestion(true)) : (() => SweetAlert.limitReached()) }
                    >
                       Add Question <FaPlusCircle className="small-icon" />
                    </button>
                </div>
                ) : (
                <>
                    <div className={`question-form ${isAddingQuestion || isEditingQuestion ? 'active' : ''}`}>
                    <input
                     type="text"
                     name="questionTitle"
                     placeholder="Question title"
                     value={isEditingQuestion ? editedQuestion.questionTitle : newQuestion.questionTitle}
                     onChange={handleInputChange}
                    />

                    <input
                    type="text"
                    name="option1"
                    placeholder="Option 1"
                    value={isEditingQuestion ? editedQuestion.option1 : newQuestion.option1}
                    onChange={handleInputChange}
                    />

                    <input
                    type="text"
                    name="option2"
                    placeholder="Option 2"
                    value={isEditingQuestion ? editedQuestion.option2 : newQuestion.option2}
                    onChange={handleInputChange}
                    />

                    <input
                    type="text"
                    name="option3"
                    placeholder="Option 3"
                    value={isEditingQuestion ? editedQuestion.option3 : newQuestion.option3}
                    onChange={handleInputChange}
                    />

                    <input
                    type="text"
                    name="option4"
                    placeholder="Option 4"
                    value={isEditingQuestion ? editedQuestion.option4 : newQuestion.option4}
                    onChange={handleInputChange}
                    />
                    
                    <select
                    name="correctOption"
                    placeholder="Correct Option"
                    value={isEditingQuestion ? editedQuestion.correctOption : newQuestion.correctOption}
                    onChange={handleInputChange}
                    >
                        <option value="">Select Correct Option</option>
                        {isEditingQuestion ? (
                        <>
                            <option value={editedQuestion.option1}>{editedQuestion.option1}</option>
                            <option value={editedQuestion.option2}>{editedQuestion.option2}</option>
                            <option value={editedQuestion.option3}>{editedQuestion.option3}</option>
                            <option value={editedQuestion.option4}>{editedQuestion.option4}</option>
                        </> 
                        ) : (
                        <>
                            <option value={newQuestion.option1}>{newQuestion.option1}</option>
                            <option value={newQuestion.option2}>{newQuestion.option2}</option>
                            <option value={newQuestion.option3}>{newQuestion.option3}</option>
                            <option value={newQuestion.option4}>{newQuestion.option4}</option>
                        </>
                        )}
                    </select>
                    <div>
                    {isAddingQuestion ? (
                        <button className="edit-button" onClick={handleAddQuestion}>Add</button>
                    ) : (
                        <button className="edit-button" onClick={handleEditQuestion}>Update</button>
                    )}
                    <button className="delete-button" onClick={() => {
                        setIsAddingQuestion(false);
                        setIsEditingQuestion(false);
                        setEditedQuestion({});
                    }}>Cancel</button>
                </div>
                </div>
            </>
          )}
          {isAddingQuestion || isEditingQuestion ? null : (
              <div className="question-list-container">
                  <div className="question-list">
                      <ul>
                          {questions.map((question) => (
                          <li key={question.questionId}>
                              <div className="question-title">{question.questionTitle}</div>
                              <div className="question-options">
                                      <div>{question.option1}</div>
                                      <div>{question.option2}</div>
                                      <div>{question.option3}</div>
                                      <div>{question.option4}</div>
                                      <div>Correct : {question.correctOption}</div>
                              </div>
                              <div className="question-actions">
                                  <button className="delete-button" onClick={() => handleDeleteButton(question.questionId)}> Delete </button>
                                  <button className="edit-button" onClick={() => handleEditClick(question)}>Edit</button>
                              </div>
                          </li>
                          ))}
                      </ul>
                  </div>
              </div>
            )}
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

export default ManageQuestion;