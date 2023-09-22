import axios from "axios";
import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import "./ManageQuestion.css";
import Swal from "sweetalert2";

const ManageQuestion = () => {
  const [questions, setQuestions] = useState([]);
  const { quizId } = useParams();
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
  }, []);

  const fetchQuestions = () => {
    axios
      .get(`http://localhost:8082/api/v1/question/byQuiz/${quizId}`)
      .then((response) => {
        setQuestions(response.data);
      })
      .catch((error) => {
        console.error("Error fetching Questions : ", error);
      });
  };

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    if (isEditingQuestion) {
      setEditedQuestion({ ...editedQuestion, [name]: value });
    } else {
      setNewQuestion({ ...newQuestion, [name]: value });
    }
  };

  const handleAddQuestion = () => {
    axios
      .post("http://localhost:8082/api/v1/question", newQuestion)
      .then(() => {
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
        console.error("Error adding question", error);
        if(error.response.data.message === "No Inputs detected"){
          Swal.fire({
            title : "Can not add Question",
            text : "Please fill all the fields",
            icon : "error"
          })
        }
      });
  };

  const handleEditQuestion = () => {
    axios
      .put(`http://localhost:8082/api/v1/question/${editedQuestion.questionId}`, editedQuestion)
      .then(() => {
        fetchQuestions();
        setIsEditingQuestion(false);
        console.log("Question updated successfully");
      })
      .catch((error) => {
        console.error("Error updating question", error);
        if(error.response.data.message === "No Inputs detected"){
          Swal.fire({
            title : "Can not add Question",
            text : "Please fill all the fields",
            icon : "error"
          })
        }
      });
  };

  const handleDeleteQuestion = (questionId) => {
    axios
      .delete(`http://localhost:8082/api/v1/question/${questionId}`)
      .then(() => {
        console.log("Question deleted: ", questionId);
        fetchQuestions();
      })
      .catch((error) => console.error("Error deleting Questions : ", error.response));
  };

  const handleEditClick = (question) => {
    setEditedQuestion({ ...question });
    setIsEditingQuestion(true);
  };

  return (
    <div className="manage-questions-container">
      <h1>{questions.length === 0 ? "No Questions Available" :"Manage Questions"}</h1>
      {!isAddingQuestion && !isEditingQuestion ? (
        <button
          className="add-category-button"
          onClick={() => setIsAddingQuestion(true)}
        >
          Add Question
        </button>
      ) : (
        <>
          <div className={`question-form ${isAddingQuestion || isEditingQuestion ? 'active' : ''}`}>
            <h2>{isAddingQuestion ? 'Add New Question' : 'Edit Question'}</h2>
            
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
                    <button className="delete-button" onClick={() => 
                      Swal.fire({
                        title : "Delete Question?",
                        text : "Are you sure you want to delete Question?",
                        icon : 'warning',
                        showCancelButton : true,
                        cancelButtonText : "No",
                        showConfirmButton : true,
                        confirmButtonText : "Delete"
                      })
                      .then((result) => {
                        if(result.isConfirmed){
                          handleDeleteQuestion(question.questionId)
                        }
                      })
                      }> Delete </button>
                    <button className="edit-button" onClick={() => handleEditClick(question)}>Edit</button>
                  </div>
                </li>
              ))}
            </ul>
          </div>
        </div>
      )}
    </div>
  );
};

export default ManageQuestion;