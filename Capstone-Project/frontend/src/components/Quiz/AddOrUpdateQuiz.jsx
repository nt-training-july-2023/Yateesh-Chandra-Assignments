import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import AdminNavBar from "../AdminNavBar";
import "../Categories/Categories.css";
import axios from "axios";

const AddOrUpdateQuiz = () => {
  const { quizId } = useParams();
  
  const navigate = useNavigate();

  const isUpdating = quizId !== undefined;

  const [quizName, setQuizName] = useState("");
  const [quizDescription, setQuizDescription] = useState("");
  const [numOfQuestions, setNumOfQuestions] = useState(0);
  const [quizNameError, setQuizNameError] = useState("");
  const [quizDescriptionError, setQuizDescriptionError] = useState("");
  const [numOfQuestionsError, setNumOfQuestionsError] = useState("");

  const fetchQuizData = () => {
    if (!isUpdating) {
      return;
    }
    axios
      .get(`http://localhost:8082/api/v1/quiz/${quizId}`)
      .then((response) => {
        const { quizName, quizDescription, numOfQuestions } = response.data;
        setQuizName(quizName);
        setQuizDescription(quizDescription);
        setNumOfQuestions(numOfQuestions);
      })
      .catch((error) => {
        console.error("Error fetching Quiz Data: ", error);
      });
  };

  useEffect(() => {
    if (isUpdating) {
      fetchQuizData();
    }
  }, [isUpdating]);

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
    if (isNaN(validNumOfQuestions) || validNumOfQuestions < 0) {
      setNumOfQuestionsError("Enter a valid number of Questions");
    } else {
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

    if (isNaN(numOfQuestions) || numOfQuestions < 0) {
      setNumOfQuestionsError("Enter a valid number of Questions");
      isValid = false;
    } else {
      setNumOfQuestionsError("");
    }

    return isValid;
  };
  const categoryId  = 2;
  const handleAddOrUpdateQuiz = async (e) => {
    e.preventDefault();

    if (!validForm()) {
      return;
    }

    console.log("category-id", categoryId);

    const quizData = {
      quizName,
      quizDescription,
      numOfQuestions,
    };

    try {
      if (isUpdating) {
        await axios.put(`http://localhost:8082/api/v1/quiz/${quizId}`, quizData);
        console.log("Quiz Updated Successfully");
      } else {
        await axios.post(`http://localhost:8082/api/v1/quiz/byCategory/${categoryId}`, quizData);
        console.log("New Quiz is added successfully.");
      }
      navigate(`/manage-category`);
    } catch (error) {
      console.error("Error: ", error);
    }
  };

  return (
    <div className="App">
      <AdminNavBar />
      <div className="add-category-container">
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
            <span className="error-message">{quizNameError}</span>

            <label>Quiz Description:</label>
            <textarea
              value={quizDescription}
              onChange={handleQuizDescriptionChange}
              placeholder="Enter Quiz Description"
            />
            <span className="error-message">{quizDescriptionError}</span>

            <label>Number of Questions:</label>
            <input
              type="number"
              value={numOfQuestions}
              onChange={handleQuizNumOfQuestions}
              placeholder="Enter Number of Questions"
            />
            <span className="error-message">{numOfQuestionsError}</span>
          </div>
          <div className="form-group">
            <div className="button-container-category">
              <button type="submit">{isUpdating ? 'Update' : 'Add'}</button>
              <button
                type="button"
                className="red-button"
                onClick={() => navigate("/manage-category")}
              >
                Cancel
              </button>
            </div>
          </div>
        </form>
      </div>
    </div>
  );
};

export default AddOrUpdateQuiz;
