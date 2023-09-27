import React, { useEffect, useState } from "react";
import AdminNavBar from "../AdminNavBar";
import UserNavBar from "../UserNavBar";
import axios from "axios";
import NotFound from "../NotFound";
import "../Categories/Categories.css";
import DeactivateBackButton from "../DeactivateBackButton";
import ResultService from "../../services/ResultService";

const Profile = () => {

    const userId = localStorage.getItem("id");
    const userName = localStorage.getItem("name");
    const userRole = localStorage.getItem("role");
    const [searchQuery, setSearchQuery] = useState('');
    const [allResults, setAllResults] = useState([]);
    
    useEffect(() => {
        if(userRole === "ADMIN"){
            fetchResults();
        } else if(userRole === "USER") {
            fetchResultsByUser();
        } else{

        }
        
    }, []);

    const fetchResults = async () => {
        try{
            ResultService.getResults()
            .then((response) => {
                setAllResults(response?.data);
            });
        } catch(error) {
            console.error("Error Fetching Results : ", error);
        }
    };

    const fetchResultsByUser = async() => {
        try{
            ResultService.getResultByUserId(userId)
            .then((response) => {
                setAllResults(response?.data);
            });
        } catch(error) {
            console.error("Error Fetching Results : ", error);
        }
    }

    const filterResults = allResults.filter((results) => {
        return results.userName.toLowerCase().includes(searchQuery.toLowerCase()) || results.email.toLowerCase().includes(searchQuery.toLowerCase());
    })

    return(
        <div>
            <DeactivateBackButton/>
            {userRole === "ADMIN" || userRole === "USER" ? (
        <>
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
                <h1 style={{fontSize :"25px", textAlign : "left", marginLeft : "20px" }}>Hello {userName},</h1>
            </div>
            {(userRole === "ADMIN" && (
                <div>
                    <div className="admin-search-bar">
                        <input
                        type="text"
                        placeholder="Search Results By Name or Email"
                        value={searchQuery}
                        onChange={(e) => setSearchQuery(e.target.value)}
                        />
                    </div>
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
                            {filterResults.reverse().map((row, index) => (
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
        </>
        )
        :(
        <>
            <NotFound/>
        </>
        )}
        </div>
    )
}

export default Profile;