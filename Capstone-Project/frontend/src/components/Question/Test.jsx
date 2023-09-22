import React, { useEffect, useState } from 'react';
import './Test.css';
import { useLocation, useNavigate, useParams } from 'react-router-dom';
import { FaStopwatch } from 'react-icons/fa';
import axios from 'axios';
import Swal from 'sweetalert2';
import clockswal from "../image/clockswal.png"

const Test = () => {
    const { quizId } = useParams();
    const navigate = useNavigate();
    const location = useLocation();
    const { timeInMin } = location.state;
    const { categoryId } = location.state;
    const userId = localStorage.getItem("id");
    const [questions, setQuestions] = useState([]);
    const [selectedOptions, setSelectedOptions] = useState([]);
    const [timer, setTimer] = useState(timeInMin * 120);
    const [loading, setLoading] = useState(true);
    const [numOfQuestionsAnswered, setNumOfQuestionsAnswered] = useState(0);
    const totalMarks = questions.length * 2;
    const [marksScored, setMarksScored] = useState(0);
    const [autoSubmitted, setAutoSubmitted] = useState(false);

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
        console.log(timer);
    }, [quizId]);

    const handleOptionSelect = (option, questionIndex) => {
        const updatedSelectedOptions = [...selectedOptions];
        updatedSelectedOptions[questionIndex] = option;
        setSelectedOptions(updatedSelectedOptions);
        const answeredQuestions = updatedSelectedOptions.filter(Boolean).length;
        setNumOfQuestionsAnswered(answeredQuestions);

        let score = 0;
        for (let i = 0; i < questions.length; i++) {
        const correctOption = questions[i].correctOption;
        const selectedOption = updatedSelectedOptions[i];

        if (selectedOption === correctOption) {
            score += 2;
        }
        }
        setMarksScored(score);
    };

    const handleAddResponses = async () => {
        const data = {
        userId,
        quizId,
        categoryId,
        numOfQuestions: questions.length,
        numOfQuestionsAnswered,
        totalMarks,
        marksScored,
        };

        try {
        await axios.post("http://localhost:8082/api/v1/response/add", data);
        } catch (error) {
        console.log(error);
        }
    };

    const handleSubmit = () => {
        handleAddResponses();
        Swal.fire({
            title : "Time up",
            text : "The quiz is submitted",
            imageUrl : clockswal,
            imageHeight : 150,
            imageWidth : 150
        }).then(() => {
            navigate("/user");
        })
        
    }

    useEffect(() => {
        const interval = setInterval(() => {
        setTimer((prevTimer) => {
            if (prevTimer > 0) {
            return prevTimer - 1;
            } else if(!autoSubmitted){
            setAutoSubmitted(true);
            handleSubmit();
            }
        });
        }, 1000);

        return () => {
        clearInterval(interval);
        };
    }, [autoSubmitted, handleSubmit]);

    const handleManualSubmit = () => {
        if(numOfQuestionsAnswered === 0){
            Swal.fire({
                title: "UnAttempted Test",
                text : "Unable to Submit as You have not attempted the test",
                icon: "error",
            })
        } else {
            Swal.fire({
                title: "Do you want to submit",
                text : "Once you submit, You will be redirected out",
                icon: "warning",
                showConfirmButton : true,
                confirmButtonText : "Ok",
                showCancelButton : true,
                cancelButtonText : "Cancel"
            }).then((result) => {
                if(result.isConfirmed){
                    handleAddResponses();
                    navigate("/user");
                }
            });
        }
        
    }

    return (
        <div className="quiz-container">
            <div className="timer"><FaStopwatch /> Time Left: {formatTime(timer)}</div>
            {loading ? (
                <div>Loading questions...</div>
            ) : questions.length > 0 ? (
                    <div className="question-container">
                        {questions.map((question, index) => (
                        <div key={index} className="question-content">
                            <p><b>{index + 1}. {question.questionTitle}</b></p>
                            <div className="options">
                                {Array.from({ length: 4 }, (_, optionIndex) => {
                                const optionKey = `option${optionIndex + 1}`;
                                const optionContent = question[optionKey];
                                return (
                                    <div
                                    key={optionIndex}
                                    className={`option ${selectedOptions[index] === optionContent ? 'selected' : null}`}
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
                            <button onClick={handleManualSubmit} className='next-button'>
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
