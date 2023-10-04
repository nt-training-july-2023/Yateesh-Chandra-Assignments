import { Link, useNavigate } from "react-router-dom";
import "./LoginRegistration.css";
import React, { useState } from "react";
import Swal from "sweetalert2";
import UserServices from "../../services/UserServices";
import SweetAlert from "../../components/SweetAlerts/SweetAlert";
import InputComponent from "../../components/InputComponent";

import { FaEye, FaEyeSlash } from "react-icons/fa";
import ButtonComponent from "../../components/ButtonComponent";

function Login() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [emailError, setEmailError] = useState("");
    const [passwordError, setPasswordError] = useState("");
    const [showPassword, setShowPassword] = useState(false);
    const navigate = useNavigate();

    const redirect = () => {
        navigate('/');
    }

    const togglePasswordVisibility = () => {
        setShowPassword(!showPassword);
    }

    const loginUserSuccessSwal = () => {
        Swal.fire({
            position: 'bottom-end',
            icon: 'success',
            title: 'Logged in successfully',
            showConfirmButton: false,
            background : "rgb(255, 252, 167)",
            timer: 2000,
            backdrop: false,
            timerProgressBar : true
        })
    }

    const loginFail = () => {
        Swal.fire({
            title : "Unable to Login",
            text : "Fill the valid details",
            icon : "error"
        })
    }

    const loginAdminSuccessSwal = () => {
        Swal.fire({
            position: 'bottom-end',
            icon: 'success',
            title: 'Logged in successfully, admin',
            showConfirmButton: false,
            backdrop : false,
            background : "yellow",
            timer: 1500,
            timerProgressBar : true
        })
    }

    const handleEmailChange = (e) => {
        setEmail(e.target.value);
        if(!email){
            setEmailError("");
            console.log("Email is required");
        }
        else{
            setEmailError("")
        }
    };

    const handlePasswordChange = (e) =>{
        setPassword(e.target.value);
        if(!password){
            setPasswordError("Enter the Password..!");
            console.log("Password is required");
        }
        else{
            setPasswordError("")
        }
    };

    const validateForm = () =>{
        let isValid = true;
        if(!email){
            setEmailError("Email is required");
            isValid = false;
        }
        else{
            setEmailError("")
        }

        if (!password) {
            setPasswordError("Password is required ");
            isValid = false;
        }
        return isValid;
    };

    const handleFormSubmit = async (event) => {
        event.preventDefault();  
        if(!validateForm()){
          loginFail();
            return;
        }
            
        try {

            const data = {
              email,
              password,
            }

            const response = await UserServices.loginUser(data);

            console.log(response);

            localStorage.setItem("role", response.data.body.userRole);
            localStorage.setItem("id", response.data.body.userId)
            localStorage.setItem("name", response.data.body.name);
            localStorage.setItem("email", response.data.body.email);

            const userRole = localStorage.getItem("role");
                console.log("User Role", userRole);
                if (userRole === "ADMIN") {
                    console.log(userRole);
                    loginAdminSuccessSwal();
                    navigate("/admin");
                } else {
                    loginUserSuccessSwal();
                    navigate("/user");
                    console.log("You are navigated to User dashboard..!");
                }

        } catch (err) {
                SweetAlert.alertError(err?.response?.data?.message);
            }
        }

      return(
        <div className="registration-form">
      <h2>Login</h2>
      <form onSubmit={handleFormSubmit}>
        
        <div className="form-group">
            <InputComponent
            className = "reg-input-fields"
            type="email"
            name="email"
            placeholder="Enter your Email"
            value={email}
            onChange={handleEmailChange}
          />
          {emailError && <div className="error">{emailError}</div>}
        </div>

        <div className="form-group">
        <div className="password-input-container">
          <InputComponent
            className = "reg-input-fields"
            type={showPassword ? "text" : "password"}
            name="password"
            placeholder="Enter your Password"
            value={password}
            onChange={handlePasswordChange}
          />
          <ButtonComponent type = "button" className = "password-toggle-button" onClick={togglePasswordVisibility} text = {showPassword ? <FaEyeSlash/> : <FaEye/>} />
          </div>
          {passwordError && <div className="error">{passwordError}</div>}
        </div>
        <div className="button-container">
            <InputComponent type="Submit" className="button-submit-login" value = "Login" />
            <InputComponent type="button" className="button-button-login" value = "Home" onClick={redirect} />
        </div>
        <h3>
            New to our Platform? <Link to="/register">Register now</Link>
        </h3>
        
    </form>
    </div>
      )

}

export default Login;