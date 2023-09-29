import React from 'react';
import "../board.css";
import UserNavBar from '../UserNavBar';
import NotFound from '../NotFound';
import DeactivateBackButton from '../DeactivateBackButton';

function UserDashBoard() {

  const userRole = localStorage.getItem("role");
  return (
    <div className="app">
      <DeactivateBackButton/>
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