import React, { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import "../LoginRegistration/Style.css"
import axios from "axios";

export default function Signup() {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [nameError, setNameError] = useState("");
  const [emailError, setEmailError] = useState("");
  const [passwordError, setPasswordError] = useState("");
  const [confirmPasswordError, setConfirmPasswordError] = useState("");
  const [phoneNumberError, setPhoneNumberError] = useState("");

  const navigate = useNavigate();
  const redirect = () => {
    navigate("/Login");
  };

  const handleNameChange = (e) => {
    setName(e.target.value);
    if (nameError) {
      setNameError("");
    }
  };

  
  const handleEmailChange = (e) => {
    const newEmail = e.target.value;
    setEmail(newEmail);
    if (!newEmail) {
      setEmailError("Email is required");
    } else if (!newEmail.endsWith("@nucleusteq.com")) {
      setEmailError("Email must be in the form of @nucleusteq.com domain");
    } else {
      setEmailError("");
    }
  };

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
    if (!password) {
      setPasswordError("Password is required ");
    } else if (password.length < 6) {
      setPasswordError("Password must be at least 6 characters");
    } else {
      setPasswordError("");
      console.log('valid password');
    }
  };

  const handleConfirmPasswordChange = (e) => {
    const newConfirmPassword = e.target.value;
    setConfirmPassword(newConfirmPassword);

    if (!newConfirmPassword) {
      setConfirmPasswordError("Confirm Password is required");
    } else if (password !== newConfirmPassword) {
      setConfirmPasswordError("Passwords do not match");
    } else {
      setConfirmPasswordError("");
      console.log('password matched');
    }
  };

  const handlephoneChange = (e) => {
    const numericInput = e.target.value.replace(/\D/g, ""); // Remove non-numeric characters
    setPhoneNumber(numericInput);
    if (numericInput.length < 10) {
      setPhoneNumberError("Phone number must be 10 digits");
    } else if (numericInput.length > 10) {
      setPhoneNumberError("Phone number must be 10 digits");
    }
    else{
      setPhoneNumberError("");
      console.log('phone number criteria satisfied');
    }
  };

  const validateForm = () => {
    let isValid = true;
    if (!name) {
      setNameError("User Name is required");
      isValid = false;
    } else {
      setNameError("");
    }
    
    if (!email) {
      setEmailError("Email is required");
      isValid = false;
    } else {
      setEmailError("");
    }

    if (!password) {
      setPasswordError("Password is required ");
      isValid = false;
    }
    if (!confirmPassword) {
      setConfirmPasswordError("Write Password to confirm");
      isValid = false;
    } else {
      setConfirmPasswordError("");
    }

    if (!phoneNumber) {
      setPhoneNumberError("Phone Number is required");
      isValid = false;
    } else {
      setPhoneNumberError("");
    }
    return isValid;
  };

  const handleFormSubmit = async (e) => {
    e.preventDefault();

    if (!validateForm()) {
      return;
    }

    try {
      const response = await axios.post("http://localhost:8082/api/v1/user/save", {
        name,
        email,
        password,
        phoneNumber,
      });
      console.log("Registration successful!", response.data);
    } catch (error) {
      console.error("Registration failed:", error);
    }
    navigate("/Login");
  };

  return (
    <div>
    <div className="wrapper">
      <h1>SIGN UP</h1>
      <form class-name = "form-control" onSubmit={handleFormSubmit}>
        <div className="input-box">
          <input
            type="text"
            placeholder="Enter User Name"
            onChange={handleNameChange}
            value={name}
          />
          {nameError && <small>{nameError}</small>}
        </div>
        
        <div className="input-box">
          <input
            type="email"
            placeholder="Email"
            onChange={handleEmailChange}
            value={email}
          />
          {emailError && <small>{emailError}</small>}
        </div>
        <div className="input-box">
          <input
            type="password"
            placeholder="Password"
            onChange={handlePasswordChange}
            value={password}
          />
          {passwordError && <small>{passwordError}</small>}
        </div>
        <div className="input-box">
          <input
            type="password"
            placeholder="Confirm password"
            onChange={handleConfirmPasswordChange}
            value={confirmPassword}
          />
          {confirmPasswordError && (
            <small>{confirmPasswordError}</small>
          )}
        </div>
        <div className="input-box">
          <input
            type="phone"
            placeholder="Phone Number"
            pattern="[0-9]*"
            onChange={handlephoneChange}
            value={phoneNumber}
          />
          {phoneNumberError && (
            <small>{phoneNumberError}</small>
          )}
        </div>
        <div className="input-box button">
          <input type="Submit" value="Register Now" />
        </div>
        <div className="text">
          <h3 onClick={redirect}>
            Already have an account? <Link to="/Login">Login now</Link>
          </h3>
        </div>
      </form>
    </div>
    </div>
  );
}