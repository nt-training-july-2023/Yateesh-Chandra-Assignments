import { Link, useNavigate } from "react-router-dom";
import "./LoginRegistration.css";
import React, { useState } from "react";
import axios from "axios";

function Login() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [emailError, setEmailError] = useState("");
    const [passwordError, setPasswordError] = useState("");
    const navigate = useNavigate();

    const redirect = () => {
        navigate('/');
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
    }
    const handleFormSubmit = async (event) => {
        event.preventDefault();
      
        if(!validateForm()){
            return;
        }
            
        try {
          const response = await axios.post("http://localhost:8082/api/v1/user/login", {
            email,
            password,
          });

          console.log(response.data);
          localStorage.setItem("role", response.data.userRole);
      
          if (response.data.message === "Email does not exist.!") {
            setEmailError("Email not exists..!");
            console.log("Oops.! The mail entered does not exist.!");
          } else if (response.data.message === "Login Successful..!") {
            const userRole = response.data.userRole;
            console.log("User Role", userRole);

            if (userRole === "USER") {
              navigate("/user");
              console.log("You are navigated to User dashboard..!");
            } else if (userRole === "ADMIN") {
               console.log(userRole);
              navigate("/admin");
              console.log("You are navigated to Admin dashboard..!");
            } else {
              setEmailError("Unable to access..!");
              console.log("You have no access to the website");
            }
          } else {
            setEmailError("Incorrect credentials");
            console.log("Detected mistake in the email ID or Password..!");
          }
         
        } catch (err) {
          setEmailError("An error occurred during login.");
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
          <input
            type="password"
            name="password"
            placeholder="Enter your Password"
            value={password}
            onChange={handlePasswordChange}
          />
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