import { Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import './App.css';
import UserDashBoard from './components/UserDashboard';
import Login from './components/LoginRegistration/Login';

import AdminDashboard from './components/AdminDashboard';
import HomePage from './components/HomePage';
import Signup from './components/LoginRegistration/Register';
import AdminCategory from './components/Categories/AdminCategory';
import ViewCategories from './components/Categories/ViewCategories';


function App() {
  return (
    <Router>
      <Routes>      
        <Route path = "/" element = {<HomePage/>} />
        <Route path = "/login" element = {<Login/>} />
        <Route path = "/register" element = {<Signup/>}/>
        <Route path = "/user/" element = {<UserDashBoard/>} />
        <Route path = "/admin/" element = {<AdminDashboard/>}/>
        <Route path="/categories" element = {<AdminCategory/>} />
        <Route path='/view' element = {<ViewCategories/>}/>
      </Routes>
    </Router>

    
  );
}

export default App;
