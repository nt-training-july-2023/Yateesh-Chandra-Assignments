import React from 'react';
import "../board.css";
import UserNavBar from '../UserNavBar';
import NotFound from '../NotFound';
import DeactivateBackButton from '../DeactivateBackButton';
import ManageCategory from '../Categories/ManageCategory';

function UserDashBoard() {

  const userRole = localStorage.getItem("role");
  const userName = localStorage.getItem("name");
  return (
    <div className="app">
      <DeactivateBackButton/>
    {userRole === "USER" ? <> 
    <UserNavBar/>
    <main>
    <h1 style={{fontSize :"25px", textAlign : "left", marginLeft : "20px", marginBottom : "0px", fontFamily : "-moz-initial" }}>Welcome {userName}, You can click on the below categories and take test</h1>
    </main>
    <ManageCategory/>
    </> : <NotFound/>
}
  </div>
  );
}

export default UserDashBoard;