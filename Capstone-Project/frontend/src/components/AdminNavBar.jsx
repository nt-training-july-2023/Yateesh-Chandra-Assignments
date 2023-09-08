import React from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';

function AdminNavBar() {

  const location = useLocation();
  const navigate = useNavigate();

  const handleSignOut = () => {
      localStorage.removeItem("role");
      navigate("/login");
  }

  return (
    <div className="app">
    <header className="sticky">
      <nav>
        <ul>
        <li><Link to="/admin" className={location.pathname === '/admin' ? 'active' : ''}>Home</Link></li>
        <li><Link to="/manage-category" className={location.pathname === '/manage-category' || location.pathname === '/add-category' ? 'active' : ''}>Categories</Link></li>
        <li><Link to="/profile" className={location.pathname === '/profile' ? 'active' : ''}>Profile</Link></li>
        <li><Link to="/about" className={location.pathname === '/about' ? 'active' : ''}>About</Link></li>
        <li className='sign-out-button'>
          <button onClick={handleSignOut}>Sign Out</button>
        </li>
        </ul>
        
      </nav>

    </header>
  </div>
  );
}

export default AdminNavBar;