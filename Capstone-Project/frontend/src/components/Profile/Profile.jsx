import React, { useEffect, useState } from "react";
import AdminNavBar from "../AdminNavBar";
import UserNavBar from "../UserNavBar";
import axios from "axios";

const Profile = () => {

    const userId = localStorage.getItem("id");
    const userName = localStorage.getItem("name");
    const userRole = localStorage.getItem("role");
    const [allResults, setAllResults] = useState([]);

    const Capitalize = (str) =>{
        return str.charAt(0).toUpperCase() + str.slice(1);
    }
    
    useEffect(() => {
        if(userRole === "ADMIN"){
            fetchResults();
        } else {
            fetchResultsByUser();
        }
        
    }, []);

    const fetchResults = async () => {
        try{
            axios.get("http://localhost:8082/api/v1/allresult")
            .then((response) => {
                setAllResults(response?.data);
            });
        } catch(error) {
            console.error("Error Fetching Results : ", error);
        }
    };

    const fetchResultsByUser = async() => {
        try{
            axios.get(`http://localhost:8082/api/v1/allresult/${userId}`)
            .then((response) => {
                setAllResults(response?.data);
            });
        } catch(error) {
            console.error("Error Fetching Results : ", error);
        }
    }

    return(
        <div>
            {( userRole === "ADMIN" &&(
                <AdminNavBar />
            )
            )} 
            {( userRole === "USER" &&(
                <UserNavBar />
            )
            )} 
            <div>
                <h1 style={{fontSize :"25px", textAlign : "left", marginLeft : "20px" }}>Hello {Capitalize(userName)},</h1>
            </div>
            {(userRole === "ADMIN" && (
                <div className="category-table-container">
                    <table className="category-table">
                        <thead>
                            <tr>
                                <th>Sno</th>
                                <th>User Name</th>
                                <th>Email</th>
                                <th>Category Name</th>
                                <th>Quiz Name</th>
                                <th>Questions Attempted</th>
                                <th>Marks Scored</th>
                                <th>Test Time Stamp</th>
                            </tr>
                        </thead>
                        
                        <tbody>
                            {allResults.reverse().map((row, index) => (
                            <tr key={index}>
                                <td>{index+1}</td>
                                <td>{row.userName}</td>
                                <td>{row.email}</td>
                                <td>{row.categoryName}</td>
                                <td>{row.quizName}</td>
                                <td style={{textAlign : "center"}}>{row.numOfQuestionsAnswered}/{row.numOfQuestions}</td>
                                <td style={{textAlign : "center"}}>{row.marksScored}/{row.totalMarks}</td>
                                <td>{row.timeStamp}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
                ))}
                
                {(userRole === "USER" && (
                <div className="category-table-container">
                    <table className="category-table">
                        <thead>
                            <tr>
                                <th>Sno</th>
                                <th>Category Name</th>
                                <th>Quiz Name</th>
                                <th>Questions Attempted</th>
                                <th>Marks Scored</th>
                                <th>Test Time Stamp</th>
                            </tr>
                        </thead>
                        
                        <tbody>
                            {allResults.reverse().map((row, index) => (
                            <tr key={index}>
                                <td>{index+1}</td>
                                <td>{row.categoryName}</td>
                                <td>{row.quizName}</td>
                                <td style={{textAlign : "center"}}>{row.numOfQuestionsAnswered}/{row.numOfQuestions}</td>
                                <td style={{textAlign : "center"}}>{row.marksScored}/{row.totalMarks}</td>
                                <td>{row.timeStamp}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
                ))}
        </div>
    )
}

export default Profile;