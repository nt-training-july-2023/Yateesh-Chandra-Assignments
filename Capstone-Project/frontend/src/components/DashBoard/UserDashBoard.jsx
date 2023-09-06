import React from 'react';
import "../board.css";
import UserNavBar from '../UserNavBar';
import NotFound from '../NotFound';

function UserDashBoard() {

  const userRole = localStorage.getItem("role");
  return (
    <div className="app">
    {userRole === "USER" ? <> 
    <UserNavBar/>
    <main>
        <h1>Welcome to the User Dashboard</h1>
    </main>
    </> : <NotFound/>
}
  </div>
  );
}

export default UserDashBoard;