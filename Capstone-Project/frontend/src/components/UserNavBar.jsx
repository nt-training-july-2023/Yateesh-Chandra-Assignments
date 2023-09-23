import React from 'react';
import { FaCode, FaHome, FaListAlt, FaPowerOff } from 'react-icons/fa';
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
                Swal.fire({
                    title : "Logout",
                    timer : 1500,
                    icon : 'success'
                });
            } 
        })
    };

    return (
        <div className="app">
            <header className="sticky">
                <nav>
                    <ul>
                        <li><Link to="/user" className={location.pathname === '/user' ? 'active' : ''}><FaHome/> Home</Link></li>
                        <li><Link to="/manage-category" className={location.pathname === '/manage-category' ? 'active' : ''}><FaCode/> Categories</Link></li>
                        <li><Link to="/profile" className={location.pathname === '/profile' ? 'active' : ''}><FaListAlt/> Report</Link></li>
                        <div className='sign-out-button'>
                            <button onClick={signOutSweetAlert}>Sign Out <FaPowerOff/></button>
                        </div>
                    </ul>
                </nav>
            </header>
        </div>
    );
}

export default UserNavBar;