import React, { useEffect, useState } from 'react';
import './Test.css';
import { useLocation, useNavigate, useParams } from 'react-router-dom';
import {FaStopwatch} from 'react-icons/fa';
import axios from 'axios';
import Swal from 'sweetalert2';

const Test = () => {
    const { quizId } = useParams();
    const navigate = useNavigate();
    const location = useLocation()
    const { timeInMin } = location.state;
    const { categoryId } = location.state;
    const userId = localStorage.getItem("id");
    const [questions, setQuestions] = useState([]);
    const [selectedOptions, setSelectedOptions] = useState([]);
    const [timer, setTimer] = useState(timeInMin * 60);
    const [loading, setLoading] = useState(true);
  
    const formatTime = (timeInSeconds) => {
      const minutes = Math.floor(timeInSeconds / 60);
      const seconds = timeInSeconds % 60;
      return `${minutes}:${seconds < 10 ? `0${seconds}` : seconds}`;
    };
  
    const apiUrl = `http://localhost:8082/api/v1/question/byQuiz/${quizId}`;
  
    const fetchQuestions = () => {
      fetch(apiUrl)
        .then((response) => response.json())
        .then((data) => {
          setQuestions(data);
          setLoading(false);
        })
        .catch((error) => {
          console.error('Error fetching questions:', error);
        });
    };
  
    useEffect(() => {
      fetchQuestions();
    }, [quizId]);
  
    useEffect(() => {
      const interval = setInterval(() => {
        setTimer((prevTimer) => {
          if (prevTimer > 0) {
            return prevTimer - 1;
          } else {
            handleSubmit();
            return prevTimer;
          }
        });
      }, 1000);
  
      return () => {
        clearInterval(interval);
      };
    }, []);
  
    const handleOptionSelect = (option, questionIndex) => {
      const updatedSelectedOptions = [...selectedOptions];
      updatedSelectedOptions[questionIndex] = option;
      setSelectedOptions(updatedSelectedOptions);
    };
  
    const calculateScore = () => {
      let score = 0;
      for (let i = 0; i < questions.length; i++) {
        const correctOption = questions[i].correctOption;
        const selectedOption = selectedOptions[i];
  
        if (selectedOption === correctOption) {
          score += 1;
        }
      }
      return score;
    };
  
    const handleSubmit = async (e) => {
      const score = calculateScore();
      const data = {
        userId,
        quizId,
        categoryId,
        numOfQuestions: questions.length,
        numOfQuestionsAnswered: questions.length,
        totalMarks: questions.length * 2,
        marksScored: score * 2,
      };
  
      try {
        axios.post("http://localhost:8082/api/v1/response/add", data).then((response) => {
          console.log(response.data);
        });
  
        Swal.fire({
          title: "Successfully Submitted",
          icon: "success",
        }).then((result) => {
          if (result.isConfirmed) {
            navigate("/user");
          }
        });
      } catch (error) {
        console.log(error);
      }
    };
  
    return (
      <div className="quiz-container">
        <div className="timer"><FaStopwatch /> Time Left: {formatTime(timer)}</div>
        {loading ? (
          <div>Loading questions...</div>
        ) : questions.length > 0 ? (
          <div className="question-container">
            {questions.map((question, index) => (
              <div key={index} className="question-content">
                <h2>Question {index + 1}</h2>
                <p><b>{question.questionTitle}</b></p>
                <div className="options">
                  {Array.from({ length: 4 }, (_, optionIndex) => {
                    const optionKey = `option${optionIndex + 1}`;
                    const optionContent = question[optionKey];
                    return (
                      <div
                        key={optionIndex}
                        className={`option ${selectedOptions[index] === optionContent ? 'selected' : ''}`}
                        onClick={() => handleOptionSelect(optionContent, index)}
                      >
                        {optionContent}
                      </div>
                    );
                  })}
                </div>
              </div>
            ))}
            <div className="navigation-buttons">
              <button onClick={handleSubmit} className='next-button'>
                Submit
              </button>
            </div>
          </div>
        ) : (
          <div>No questions available.</div>
        )}
      </div>
    );
};

export default Test;