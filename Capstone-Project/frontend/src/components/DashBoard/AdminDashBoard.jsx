import React from 'react';
import "../board.css";
import AdminNavBar from '../AdminNavBar';
import NotFound from '../NotFound';

function AdminDashBoard() {
  const userRole = localStorage.getItem("role");

  return (
    <div className="app">
     {userRole === 'ADMIN' ? <>
    <AdminNavBar/>
    <main>
        <h1>Welcome to the Admin Dashboard</h1>
    </main></> : <NotFound/>
}
  </div>
  );
}

export default AdminDashBoard;