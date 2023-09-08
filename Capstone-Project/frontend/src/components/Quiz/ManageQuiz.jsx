import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import "./Quizzes.css"; // Import your CSS file

import AdminNavBar from "../AdminNavBar";

const ManageQuiz = () => {
  const { categoryId } = useParams();
  const {categoryName} = useParams();
  const [quizzes, setQuizzes] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    fetchQuizzes();
  }, []);

  const fetchQuizzes = () => {
    try {
      axios
        .get(`http://localhost:8082/api/v1/quiz`)
        .then((response) => {
          console.log('categoryId:', categoryId);
          setQuizzes(response.data);
        });
    } catch (error) {
      console.error("Error fetching quizzes : ", error);
    }
  };


  return (
    <div>
        <AdminNavBar />
    <div className="manage-quiz-container">
      <div className="quiz-list-container">
        <h1>Quizzes Available in {categoryName}</h1>
        <button
          className="add-quiz-button"
          onClick={() => navigate('/add-quiz')}
        >
          Add Quiz
        </button>
        <div className="quiz-grid">
          {quizzes.map((quiz) => (
            <div className="quiz-card" key={quiz.quizId}>
              <h2>{quiz.quizName}</h2>
              <p>{quiz.quizDescription}</p>
              <p>Total No of Questions : <b>{quiz.numOfQuestions}</b></p>
              <div className="open-quiz">
                <button className="edit-button">Edit</button>
                <button className="delete-button">Delete</button>
                <button className="open-button">Open</button>
              </div>
            </div>
          ))}
        </div>
      </div>
      </div>
    </div>
  );
};

export default ManageQuiz;
