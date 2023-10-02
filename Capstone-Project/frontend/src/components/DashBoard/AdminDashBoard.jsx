import React, { useEffect, useState } from 'react';
import "../board.css";
import AdminNavBar from '../AdminNavBar';
import NotFound from '../NotFound';
import UserServices from '../../services/UserServices';

function AdminDashBoard() {
  const [users, setUsers] = useState([]);
  const [searchQuery,setSearchQuery] = useState('');
  const userRole = localStorage.getItem("role");

  useEffect(() => {
    fetchUsers();
  }, [])

  const fetchUsers = async () =>{
    await UserServices.getUsers()
    .then((response) => setUsers(response.data))
    .catch((error) => console.log(error));
  }

  const filterUser = users.filter((users) => {
    return users.name.toLowerCase().includes(searchQuery.toLowerCase()) || users.email.toLowerCase().includes(searchQuery.toLowerCase());
  });

  return (
    <div className="app">
     {userRole === 'ADMIN' ? <>
    <AdminNavBar/>
    <main>
        <h1  style={{fontSize :"25px", textAlign : "left", marginLeft : "20px", marginBottom : "0px"}}>Welcome to the Admin Dashboard, Here are the Registered Users :</h1>
    </main>
    <div>
        <div className="admin-search-bar">
            <input
            type="text"
            placeholder="Search Users By Name or Email"
            value={searchQuery}
            onChange={(e) => setSearchQuery(e.target.value)}
            />
        </div>

        <div className="category-table-container">
            <table className="category-table">
            {users.length !== 0 ? (
                <>
                <thead>
                    <tr>
                        <th>Sno</th>
                        <th>User Name</th>
                        <th>Email</th>
                        <th>Phone Number</th>
                    </tr>
                </thead>
                
                <tbody>
                    {filterUser.reverse().map((row, index) => (
                    <tr key={index}>
                        <td>{index+1}</td>
                        <td>{row.name}</td>
                        <td>{row.email}</td>
                        <td>{row.phoneNumber}</td>
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
    
    </> : <NotFound/>
}
  </div>
  );
}

export default AdminDashBoard;