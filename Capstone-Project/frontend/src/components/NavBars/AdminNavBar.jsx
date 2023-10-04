import React from 'react';
import { FaCode, FaHome, FaListAlt, FaPowerOff } from 'react-icons/fa';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import Swal from 'sweetalert2';
import IconButton from '../IconButton';

function AdminNavBar() {

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
                Swal.fire({
                    title : "Logged out successfully",
                    position : 'bottom-right',
                    timer : 1500,
                    timerProgressBar : true,
                    background : `rgb(255, 252, 167)`,
                    icon : 'success',
                    showConfirmButton : false
                });
            } 
        })
    };

    return (
        <div className="app">
          <header className="sticky">
              <nav>
                  <ul>
                      <li><Link to="/admin" className={location.pathname === '/admin' ? 'active' : ''}><FaHome/> Home</Link></li>
                      <li><Link to="/manage-category" className={location.pathname === '/manage-category' || location.pathname === '/add-category' ? 'active' : ''}><FaCode/> Categories</Link></li>
                      <li><Link to="/profile" className={location.pathname === '/profile' ? 'active' : ''}><FaListAlt/> User Activity</Link></li>
                      <li className='sign-out-button'>
                          <IconButton className='sign-button' onClick={signOutSweetAlert} text="Sign Out " icon={<FaPowerOff/>} />
                      </li>
                  </ul>
              </nav>
          </header>
      </div>
    );
}

export default AdminNavBar;