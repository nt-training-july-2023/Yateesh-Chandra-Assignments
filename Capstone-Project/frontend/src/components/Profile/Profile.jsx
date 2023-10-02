import React, { useEffect, useState } from "react";
import AdminNavBar from "../AdminNavBar";
import UserNavBar from "../UserNavBar";
import NotFound from "../NotFound";
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
            console.log(allResults.length);
        } else if(userRole === "USER") {
            fetchResultsByUser();
        } else{

        }
        
    }, []);

    const fetchResults = async () => {
        try {
          const response = await ResultService.getResults();
          const results = response?.data;
          results.sort(sortByTimeStamp);
          setAllResults(results);
        } catch (error) {
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

    const sortByTimeStamp = (a, b) => {
        return new Date(b.timeStamp) - new Date(a.timeStamp);
    }

    const filterResults = allResults.filter((results) => {
        return results.userName.toLowerCase().includes(searchQuery.toLowerCase()) || results.email.toLowerCase().includes(searchQuery.toLowerCase());
    }).sort(sortByTimeStamp);

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
                <h1 style={{fontSize :"25px", textAlign : "left", marginLeft : "20px" }}>Hello</h1>
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
                        {allResults.length !== 0 ? (
                            <>
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
                            </>
                        ) : (
                            <h1 style={{fontSize : "22px"}}>
                                No User has taken test as of now.
                            </h1>
                        )}
                        </table>
                    </div>
                </div>
                ))}
                
                {(userRole === "USER" && (
                <div className="category-table-container">
                    <table className="category-table">
                    {allResults.length !== 0 ? (
                            <>
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
                        </>
                        ) : (
                            <h1 style={{fontSize : "22px"}}>
                                You have not taken any tests so far. Start assessing now.
                            </h1>
                        )}
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