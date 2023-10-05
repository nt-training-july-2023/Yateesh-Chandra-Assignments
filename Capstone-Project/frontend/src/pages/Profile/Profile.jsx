import React, { useEffect, useState } from "react";
import AdminNavBar from '../../components/NavBars/AdminNavBar';
import UserNavBar from '../../components/NavBars/UserNavBar';
import NotFound from '../HomePage/NotFound';
import DeactivateBackButton from '../../components/DeactivateBackButton';
import ResultService from "../../services/ResultService";
import Header1 from "../../components/Header1";
import { FaInfoCircle } from "react-icons/fa";

const Profile = () => {

    const userId = localStorage.getItem("id");
    const userRole = localStorage.getItem("role");
    const [searchQuery, setSearchQuery] = useState('');
    const [allResults, setAllResults] = useState([]);
    const userName = localStorage.getItem("name");
    
    useEffect(() => {
        if(userRole === "ADMIN"){
            fetchResults();
        } else if(userRole === "USER") {
            fetchResultsByUser();
        } else{

        }
        
    }, []);

    const fetchResults = async () => {
        const response = await ResultService.getResults();
        const results = response?.data?.body;
        results.sort(sortByTimeStamp);
        setAllResults(results);
      };

    const fetchResultsByUser = async() => {
        ResultService.getResultByUserId(userId)
        .then((response) => {
            setAllResults(response?.data?.body);
        });
    }

    const sortByTimeStamp = (a, b) => {
        return new Date(b.timeStamp) - new Date(a.timeStamp);
    }

    const filterResults = allResults.filter((results) => {
        return results.userName.toLowerCase().includes(searchQuery.toLowerCase()) || results.email.toLowerCase().includes(searchQuery.toLowerCase());
    }).sort(sortByTimeStamp);

    const search = (e) => {
        setSearchQuery(e.target.value)
    }
    return(
        <div>
            <DeactivateBackButton/>
            {userRole === "ADMIN" || userRole === "USER" ? (
            <>
                <div>
                    {( userRole === "ADMIN" &&(
                        <AdminNavBar />
                    ))} 

                    {( userRole === "USER" &&(
                        <UserNavBar />
                    ))} 
                    
                    <div className="proper-profile">
                        <Header1 className = "h1-profile arial" text={userRole === "ADMIN" ? "The Users' Activity of Assessments is viewed here:" : `Dear ${userName}, The Report of your Assessments can be viewed here..!`}/>
                        {userRole === "USER" && (<span className="info-icon"><FaInfoCircle className="large-icon"/><span className="info-tooltip">Due to slow network, the result may not be displayed immediately. You can refresh to update</span></span>)}
                    </div>

                    {(userRole === "ADMIN" && (
                        <div>
                            <div className="admin-search-bar">
                                <input
                                type="text"
                                className="search-input-over"
                                placeholder="Search Results By Name or Email"
                                value={searchQuery}
                                onChange={search}
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
                                            {filterResults.map((row, index) => (
                                                <tr key={index}>
                                                    <td>{index+1}</td>
                                                    <td>{row.userName}</td>
                                                    <td>{row.email}</td>
                                                    <td>{row.categoryName}</td>
                                                    <td>{row.quizName}</td>
                                                    <td className="td-center">{row.numOfQuestionsAnswered}/{row.numOfQuestions}</td>
                                                    <td className="td-center">{row.marksScored}/{row.totalMarks}</td>
                                                    <td>{row.timeStamp}</td>
                                                </tr>
                                            ))}
                                        </tbody>
                                    </>
                                ) : (
                                    <div className="AppCenter">
                                        <Header1 className = "text-22" text = "No User has taken tests as of now"/>
                                    </div>
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
                                            <td className="td-center">{row.numOfQuestionsAnswered}/{row.numOfQuestions}</td>
                                            <td className="td-center">{row.marksScored}/{row.totalMarks}</td>
                                            <td>{row.timeStamp}</td>
                                            </tr>
                                        ))}
                                    </tbody>
                                </>
                            ) : (
                            
                            <div className="AppCenter">
                                <Header1 className = "text-22" text = {`You have not taken any tests so far.`} />
                                <Header1 className = "text-22" text = {`Start assessing now from Home .`} />        
                            </div>
                            
                            )}
                        </table>
                    </div>
                    ))}
                </div>
            </>
            ):(
            <>
                <NotFound/>
            </>
            )}
        </div>
    )
}

export default Profile;