import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import AdminDashBoard from './components/DashBoard/AdminDashBoard';
import HomePage from './components/HomePage';
import Registration from './components/LoginRegistration/Registration';
import Login from './components/LoginRegistration/Login';
import UserDashBoard from './components/DashBoard/UserDashBoard';
import ManageCategory from './components/Categories/ManageCategory';
import AddOrUpdateCategory from './components/Categories/AddOrUpdateCategory';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        {/* <NavBar/> */}
        <Routes>
        <Route path = "/" element = {<HomePage/>}/>
        <Route path = "/register" element = {<Registration/>}/>
        <Route path = "/login" element = {<Login/>}/>
        <Route path = "/admin" element = {<AdminDashBoard/>}/>
        <Route path = "/manage-category" element = {<ManageCategory/>} />
        <Route path = "/add-category" element = {<AddOrUpdateCategory/>} />
        <Route path = "/add-category/:categoryId" element = {<AddOrUpdateCategory/>} />
        <Route path = "/user" element = {<UserDashBoard/>}/>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
