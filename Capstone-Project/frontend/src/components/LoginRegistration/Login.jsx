import { Link, useNavigate } from "react-router-dom";
import "./LoginRegistration.css";
import React, { useState } from "react";
import axios from "axios";
import Swal from "sweetalert2";

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
            background : "yellow",
            timer: 2000,
            backdrop: false,
            timerProgressBar : true
        })
    }

    const alertEmailError = () => {
        Swal.fire({
            title : "Unregistered Email",
            text : "No such Email is registered before(run backend)",
            icon : "error"
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
          const response = await axios.post("http://localhost:8082/api/v1/user/login", {
            email,
            password,
          });

          console.log(response.data);
          localStorage.setItem("role", response.data.userRole);
          localStorage.setItem("id", response.data.userId)
          localStorage.setItem("name", response.data.name);
          localStorage.setItem("email", response.data.email);
      
          if (response.data.message === "Email does not exist.!") {
            setEmailError("Email not exists..!");
            console.log("Oops.! The mail entered does not exist.!");
          } else if (response.data.message === "Login Successful..!") {
            const userRole = response.data.userRole;
            console.log("User Role", userRole);

            if (userRole === "USER") {
              loginUserSuccessSwal();
              navigate("/user");
              console.log("You are navigated to User dashboard..!");
            } else if (userRole === "ADMIN") {
               console.log(userRole);
               loginAdminSuccessSwal();
              navigate("/admin");
              console.log("You are navigated to Admin dashboard..!");
            } else {
              setEmailError("Unable to access..!");
              console.log("You have no access to the website");
            }
          } else {
            loginFail();
            setEmailError("Incorrect credentials");
            console.log("Detected mistake in the email ID or Password..!");
          }
         
        } catch (err) {
          alertEmailError();
          setEmailError("No such email exists.");
          console.log(err);
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