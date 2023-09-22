import React from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import Swal from 'sweetalert2';

function UserNavBar() {

  const location = useLocation();
  const navigate = useNavigate();
  
  const handleSignOut = () => {
    localStorage.clear();
    navigate("/login");
  }

  const signOutSweetAlert = () => {
    Swal.fire({
      title : "Are you Sure you want to Sign out?",
      showConfirmButton : true,
      confirmButtonText : "Sign out",
      showCancelButton : true,
      cancelButtonText : "No",
      icon : "warning"
    }).then((result) => {
      if(result.isConfirmed){
        handleSignOut();
        Swal.fire("Logged out", "", "success");
      } 
    })
    
  };

  return (
    <div className="app">
    <header className="sticky">
      <nav>
        <ul>
        <li><Link to="/user" className={location.pathname === '/user' ? 'active' : ''}>Home</Link></li>
        <li><Link to="/manage-category" className={location.pathname === '/manage-category' ? 'active' : ''}>Categories</Link></li>
        <li><Link to="/profile" className={location.pathname === '/profile' ? 'active' : ''}>Report</Link></li>
        <li className='sign-out-button'>
          <button onClick={signOutSweetAlert}>Sign Out</button>
        </li>
        
        </ul>
        
      </nav>

    </header>
  </div>
  );
}

export default UserNavBar;