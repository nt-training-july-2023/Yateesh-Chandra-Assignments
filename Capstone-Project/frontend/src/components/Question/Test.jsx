import React, { useEffect, useState } from 'react';
import './Test.css';
import { useLocation, useNavigate, useParams } from 'react-router-dom';
import Swal from 'sweetalert2';
import clockswal from "../image/clockswal.png";
import oopsswal from "../image/oopsswal.png";
import instructionswal from "../image/instructionswal.png";
import DeactivateBackButton from '../DeactivateBackButton';
import NotFound from '../NotFound';
import QuestionService from '../../services/QuestionService';
import ResponseService from '../../services/ResponseService';
import SweetAlert from '../SweetAlerts/SweetAlert';
import { format } from 'date-fns';
import TimerNavBar from '../TimerNavBar';

const Test = () => {

    const { quizId } = useParams();
    const navigate = useNavigate();
    const location = useLocation();
    const { timeInMin } = location.state || {};
    const categoryId = localStorage.getItem("categoryId");
    const userId = localStorage.getItem("id");
    const userRole = localStorage.getItem("role");
    const [questions, setQuestions] = useState([]);
    const [loading, setLoading] = useState(true);
    const totalMarks = questions.length * 2;
    const [autoSubmitted, setAutoSubmitted] = useState(false);
    const [instructionsConfirmed, setInstructionsConfirmed] = useState(false);
    const [numOfQuestionsAnswered, setNumOfQuestionsAnswered] = useState(() => {
        const storedNumberOfAttempted = localStorage.getItem('numberOfAttempted');
        return storedNumberOfAttempted ? parseInt(storedNumberOfAttempted) : 0;
    }); 

    const [marksScored, setMarksScored] = useState(() => {
        const storedMarksScored = localStorage.getItem('marksScored');
        return storedMarksScored ? parseInt(storedMarksScored) : 0;
    });

    const [startTime, setStartTime] = useState(() => {
        const storedStartTime = localStorage.getItem('startTime');
        return storedStartTime ? parseInt(storedStartTime) : null;
    });

    const [selectedOptions, setSelectedOptions] = useState(() => {
        const storedOptions = localStorage.getItem('selectedOptions');
        return storedOptions ? JSON.parse(storedOptions) : [];
    });

    const [timer, setTimer] = useState(() => {
        const storedTimer = localStorage.getItem('timer');
        if (storedTimer && startTime) {

            const currentTime = new Date().getTime();
            const elapsedTimeInSeconds = Math.floor((currentTime - startTime) / 1000);
            const remainingTime = parseInt(storedTimer) - elapsedTimeInSeconds;
            return remainingTime > 0 ? remainingTime : 0;

        } else {
            return timeInMin * 60;
        }
    });

    const formatTime = (timeInSeconds) => {
        const minutes = Math.floor(timeInSeconds / 60);
        const seconds = timeInSeconds % 60;
        return `${minutes}:${seconds < 10 ? `0${seconds}` : seconds}`;
    };

    const fetchQuestions = () => {
        QuestionService.getQuestionsByQuizId(quizId)
            .then((response) => {
                if (response.data.length === 0) {
                    Swal.fire({
                        title: "No Questions available",
                        text: "Kindly take another Quiz",
                        imageUrl: oopsswal,
                        imageHeight: 100,
                        imageWidth: 150,
                    }).then(() => {
                        navigate(`/manage-quiz/${categoryId}`);
                    })
                } else {
                    setQuestions(response.data);
                    setLoading(false);
                }
            })
            .catch((error) => {
                console.error('Error fetching questions:', error);
            });
    };

    useEffect(() => {
        if (userRole === "USER") {
            fetchQuestions();
        }
    }, [quizId]);

    
    const handleOptionSelect = (option, questionIndex) => {
        const updatedSelectedOptions = [...selectedOptions];
        if (updatedSelectedOptions[questionIndex] === option) {
            updatedSelectedOptions[questionIndex] = null;
        } else {
            updatedSelectedOptions[questionIndex] = option;
        }

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

        localStorage.setItem('numberOfAttempted', answeredQuestions.toString());
        localStorage.setItem('marksScored', score.toString());
    };

    const clearLocalStorage = () => {
        localStorage.removeItem('selectedOptions');
        localStorage.removeItem('startTime');
        localStorage.removeItem('timer');
        localStorage.removeItem('instructionShown');
        localStorage.removeItem("pageRefreshed");
        localStorage.removeItem('numberOfAttempted');
        localStorage.removeItem('marksScored');
    }

    const handleAddResponses = async () => {

        const formattedTime = format(new Date(startTime), 'dd-MM-yyyy HH:mm:ss');

        const data = {
            userId,
            quizId,
            categoryId,
            numOfQuestions: questions.length,
            numOfQuestionsAnswered,
            totalMarks,
            marksScored,
            timeStamp: formattedTime
        };

        try {
            await ResponseService.postResponse(data);
            console.log(data);
        } catch (error) {
            console.log(error);
        }
    };

    const handleSubmit = () => {
        handleAddResponses();
        Swal.fire({
            title: "Time up",
            text: "Don't worry, The quiz is submitted",
            imageUrl: clockswal,
            imageHeight: 150,
            imageWidth: 150,
        }).then((result) => {
            if (result.isConfirmed) {
                SweetAlert.quizSubmitted();
                clearLocalStorage();
            }
            navigate("/profile");
        })
    };

    useEffect(() => {
        if (userRole === "USER") {
            const instructionShown = localStorage.getItem("instructionShown");
            if (questions.length > 0 && !instructionsConfirmed && !instructionShown) {
                Swal.fire({
                    title: "Instructions for the Test",
                    width: "900px",
                    padding: '3em',
                    color: 'black',
                    backdrop: `
                        rgb(240, 240, 240, 0.8)
                    `,
                    imageUrl: instructionswal,
                    imageWidth: 100,
                    allowOutsideClick: false,
                    showConfirmButton: true,
                    confirmButtonText: "Start",
                    html: `
                        <ol>
                        <li>Each Question carries Two Marks.</li><br>
                        <li>This is a Timed Test. Look at the timer.</li><br>
                        <li>Questions are of "Choose the Correct Answer" type.</li><br>
                        <li>There is no negative marking.</li><br>
                        <p style="text-align : left; margin-top : 10px;"><strong>NOTE: IF YOU RELOAD, IT AUTOMATICALLY GETS SUBMITTED.</strong>
                        </ol>
                    `,
                }).then((response) => {
                    if (response.isConfirmed) {
                        setInstructionsConfirmed(true);
                        const currentTime = new Date().getTime();
                        setStartTime(currentTime);

                        localStorage.setItem("instructionShown", "true");
                        localStorage.setItem("startTime", currentTime.toString());
                    }
                });
            } else {

                const pageRefreshed = localStorage.getItem("pageRefreshed");
                if (pageRefreshed) {
                    Swal.fire({
                        title: "Page Reloaded",
                        text: "Your Responses are posted",
                        icon: "info",
                        showConfirmButton: true,
                        allowOutsideClick: false,
                        backdrop: 
                        `
                            rgb(200, 200, 200, 0.9)
                        `,
                        confirmButtonText: "Ok",

                    }).then((result) => {
                        if (result.isConfirmed) {
                            handleAddResponses();
                            clearLocalStorage();
                            navigate("/profile");
                        }
                    });
                }
            }
        }
    }, [userRole, questions, instructionsConfirmed]);


    useEffect(() => {
        if (userRole === "USER" && instructionsConfirmed) {
            let interval;

            const startTimer = () => {
                if (startTime) {
                    const currentTime = new Date().getTime();
                    const elapsedTimeInSeconds = Math.floor((currentTime - startTime) / 1000);
                    const remainingTime = Math.max(timeInMin * 60 - elapsedTimeInSeconds, 0);

                    setTimer(remainingTime);

                    localStorage.setItem('timer', remainingTime.toString());
                    localStorage.setItem('startTime', startTime.toString());

                    if (remainingTime <= 0 && !autoSubmitted) {
                        setAutoSubmitted(true);
                        handleSubmit();
                        clearInterval(interval);
                    }
                }
            };

            if (!startTime) {
                const currentTime = new Date().getTime();
                setStartTime(currentTime);
                localStorage.setItem('startTime', currentTime.toString());
            }

            interval = setInterval(startTimer, 1000);

            return () => {
                clearInterval(interval);
            };
        }
    }, [instructionsConfirmed, autoSubmitted, handleSubmit, timeInMin, userRole, startTime]);

    const handleManualSubmit = () => {
        if (numOfQuestionsAnswered === 0) {
            Swal.fire({
                title: "UnAttempted Test",
                text: "Unable to Submit as You have not attempted the test",
                icon: "error",
            })
        } else {
            Swal.fire({
                title: "Do you want to submit",
                text: "Once you submit, You will be redirected out",
                icon: "warning",
                showConfirmButton: true,
                confirmButtonText: "Ok",
                showCancelButton: true,
                cancelButtonText: "Cancel"
            }).then((result) => {
                if (result.isConfirmed) {
                    handleAddResponses();
                    SweetAlert.manualQuizSubmitted();
                    clearLocalStorage();
                    navigate("/profile");
                }
            });
        }
    };

    useEffect(() => {
        const handleBeforeUnload = () => {        
            localStorage.setItem("pageRefreshed", "true");
        };
        window.addEventListener('beforeunload', handleBeforeUnload);
        return () => {
            window.removeEventListener('beforeunload', handleBeforeUnload);
        };
    }, []);

    return (
        <div>
            <DeactivateBackButton />
            <TimerNavBar timerValue={formatTime(timer)} className={timer < 30 ? "timer-out" : "timer"} />
            {(userRole === "USER" ? (
                <div className="quiz-container">
                    {loading ? (
                        <div>Loading questions... No Questions as of now</div>
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
                        <div>
                            No questions available.
                        </div>
                    )}

                </div>
            ) : (
                <>
                    <NotFound />
                </>
            ))}
        </div>
    );

};

export default Test;
