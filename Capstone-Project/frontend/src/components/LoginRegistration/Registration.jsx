import { useNavigate, Link } from "react-router-dom";
import "../LoginRegistration/LoginRegistration.css"
import { useState } from "react";
import axios from "axios";
import Swal from "sweetalert2";

export default function Registration() {
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

  const [showPassword, setShowPassword] = useState(false);
  const [showConfirmPassword, setShowConfirmPassword] = useState(false);

  const navigate = useNavigate();

  const handleNameChange = (e) => {
    setName(e.target.value);
    if (nameError) {
      setNameError("");
    }
  };

  const registrationSuccessFireAlert = () => {
    Swal.fire({
      title : "Successfully Registered",
      text : "Hurray, Now you are our subscriber.!",
      icon : "success"
    })
    .then((res) => {
      
    })
    navigate("/login")
  }

  const registrationFailureFireAlert = () => {
    Swal.fire({
      title : "Unable to register",
      text : "Please Modify all error credentials",
      icon : "error"
    })
  }

  const registrationEmailAlreadyExist = () => {
    Swal.fire({
      title : "Cannot proceed",
      text : "An account is registered previously under this account. Either Login or create with other mail",
      icon : "error"
    })
  }

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
    } else if (password.length < 5) {
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
    const numericInput = e.target.value.replace(/\D/g, "");
    setPhoneNumber(numericInput);
    if (numericInput.length < 10) {
      setPhoneNumberError("Phone number must be 10 digits");
    } 
    else{
      setPhoneNumberError("");
      console.log('phone number criteria satisfied');
    }
  };

  const togglePasswordVisibility = () => {
    setShowPassword(!showPassword);
  }

  const toggleConfirmPasswordVisibility = () => {
    setShowConfirmPassword(!showConfirmPassword);
  }

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
      isValid=false;
    } else if (!email.endsWith("@nucleusteq.com")) {
      isValid=false;
      setEmailError("Email must be in the form of @nucleusteq.com domain");
      console.log('unable to register enter valid mail');
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
    }
    else if(phoneNumber.length<10){
      setPhoneNumberError ("Phone number is less than 10 digits.")
      isValid=false;
      console.log("The number should be of 10 digits");
    }
    
    else {
      setPhoneNumberError("");
    }
    return isValid;
  };

  const handleFormSubmit = async (e) => {
    e.preventDefault();

    if (!validateForm()) {
      registrationFailureFireAlert();
      return;
    }

    try {
      const response = await axios.post("http://localhost:8082/api/v1/user/save", {
        name,
        email,
        password,
        phoneNumber,
      });
      if(response?.status === 200){
        console.log("successful")
        registrationSuccessFireAlert();      
      }
    } catch (error) {
      console.log(error)
      if(error?.response?.data?.message === ""){
        setEmailError("This email already exists")
        registrationEmailAlreadyExist();
        console.log("Oops.! The mail entered does not exist.!");
      }
      if(error?.response?.data?.message === "Email already Exists"){
        setEmailError("This email already exists")
        registrationEmailAlreadyExist();
        console.log("Oops.! The mail entered does not exist.!");
      }
    }
    
  };

  return(
    <div className="registration-form">
      <h2>Signup</h2>
      <form onSubmit={handleFormSubmit}>
        
        <div className="form-group">
          <input
            type="text"
            name="name"
            placeholder="Enter your Name"
            value={name}
            onChange={handleNameChange}
          />
          {nameError && <div className="error">{nameError}</div>}
        </div>
        
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

        <div className="form-group">
        <div className="password-input-container">
          <input
            type={showConfirmPassword ? "text" :"password"}
            name="confirmPassword"
            placeholder="Enter Password to confirm"
            value={confirmPassword}
            onChange={handleConfirmPasswordChange}
          />
          <button type = "button" className = "password-toggle-button" onClick={toggleConfirmPasswordVisibility}>
            {showConfirmPassword ? "Hide" : "Show"}
          </button>
          </div>
          {confirmPasswordError && <div className="error">{confirmPasswordError}</div>}
        </div>

        <div className="form-group">
          <input
            type="phone"
            name="phoneNumber"
            pattern="[0-9]*"
            placeholder="Enter Phone Number"
            value={phoneNumber}
            onChange={handlephoneChange}
          />
          {phoneNumberError && <div className="error">{phoneNumberError}</div>}
        </div>
            <input type="Submit" value="Register Now" />
            <div className="text">
            <h3>
                Already our Subscriber? <Link to="/Login">Login now</Link> 
            </h3>
            </div>
      </form>
    </div>
  );
}

