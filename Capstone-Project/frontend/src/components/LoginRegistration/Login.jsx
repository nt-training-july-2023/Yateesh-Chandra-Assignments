import { Link, useNavigate } from "react-router-dom";
import "./LoginRegistration.css";
import React, { useState } from "react";
import Swal from "sweetalert2";
import UserServices from "../../services/UserServices";
import SweetAlert from "../SweetAlerts/SweetAlert";

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

            localStorage.setItem("role", response.data.userRole);
            localStorage.setItem("id", response.data.userId)
            localStorage.setItem("name", response.data.name);
            localStorage.setItem("email", response.data.email);

            const userRole = localStorage.getItem("role");

            if(response?.status === 200){
                
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
              }
        } catch (err) {
            if(err?.response?.data?.message === "Invalid Password" 
            || err?.response?.data?.message === "Email does not exist.!" ){
                SweetAlert.alertError()
            }
        }
      };

      return(
        <div className="registration-form">
      <h2>Login</h2>
      <form onSubmit={handleFormSubmit}>
        
        <div className="form-group">
          <input
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
          <input
            type={showPassword ? "text" : "password"}
            name="password"
            placeholder="Enter your Password"
            value={password}
            onChange={handlePasswordChange}
          />
          <button type = "button" className="password-toggle-button" onClick={togglePasswordVisibility}>
            {showPassword ? "Hide" : "Show"}
          </button>
          </div>
          {passwordError && <div className="error">{passwordError}</div>}
        </div>
        <div className="button-container">
            <input type="Submit" value="Login" />
            <input type="button" value="Home" onClick={redirect}/>
        </div>
        <h3>
            New to our Platform? <Link to="/register">Register now</Link>
        </h3>
        
    </form>
    </div>
      )

}

export default Login;