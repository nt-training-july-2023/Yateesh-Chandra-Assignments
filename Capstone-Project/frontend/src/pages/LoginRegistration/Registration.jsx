import { useNavigate, Link } from "react-router-dom";
import "../LoginRegistration/LoginRegistration.css";
import { useState } from "react";
import successfulswal from "../../assets/image/successfulswal.png"
import UserServices from "../../services/UserServices";
import SweetAlert from "../../components/SweetAlerts/SweetAlert";
import { FaEye, FaEyeSlash } from "react-icons/fa";
import ButtonComponent from "../../components/ButtonComponent";
import InputComponent from "../../components/InputComponent";

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
        }
    };

    const handlephoneChange = (e) => {
        const numericInput = e.target.value.replace(/\D/g, "");
        setPhoneNumber(numericInput);
        if (numericInput.length < 10) {
            setPhoneNumberError("Phone number must be 10 digits");
        } else {
            setPhoneNumberError("");
        }
    };

    const togglePasswordVisibility = () => {
        setShowPassword(!showPassword);
    };

    const toggleConfirmPasswordVisibility = () => {
        setShowConfirmPassword(!showConfirmPassword);
    };

    const validateForm = () => {
        let isValid = true;
        if (!name) {
            setNameError("User Name is required");
            isValid = false;
        } else if (name.startsWith(" ")){
            setNameError("Name cannot start with spaces");
            isValid = false;
        } else{
            setNameError("");
        }

        if (!email) {
            setEmailError("Email is required");
            isValid = false;
        } else if (!email.endsWith("@nucleusteq.com")) {
            isValid = false;
            setEmailError("Email must be in the form of @nucleusteq.com domain");
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

        if (password !== confirmPassword) {
            setConfirmPasswordError("Passwords did not match");
            isValid = false;
        }

        if (!phoneNumber) {
            setPhoneNumberError("Phone Number is required");
            isValid = false;
        } else if (phoneNumber.length < 10 || phoneNumber.length > 10) {
            setPhoneNumberError("Phone number must be 10 digits.");
            isValid = false;
        } else {
            setPhoneNumberError("");
        }
        return isValid;
    };

    const handleFormSubmit = async (e) => {
        e.preventDefault();

        if (!validateForm()) {
            SweetAlert.registrationFailureFireAlert("Please Modify all error credentials");
            return;
        }

        try {

            const data = {
                name,
                email,
                password,
                phoneNumber,
            };

            await UserServices.registerUser(data);
            SweetAlert.registrationSuccessFireAlert(successfulswal);
            navigate("/login");

        } catch (error) {
            setEmailError("This email already exists");
            SweetAlert.registrationFailureFireAlert(error.response.data.message);
        }
    };

    return(
        <div className="registration-form">
            <h2>Signup</h2>
            <form onSubmit={handleFormSubmit}>
                <div className="form-group">
                    <InputComponent
                    className = "reg-input-fields"
                    type="text"
                    name="name"
                    placeholder="Enter your Name"
                    value={name}
                    onChange={handleNameChange}
                    />
                    {nameError && <div className="error">{nameError}</div>}
                </div>
              
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
                        
                        <ButtonComponent
                        type = "button"
                        className = "password-toggle-button"
                        onClick={togglePasswordVisibility}
                        text = {showPassword ? <FaEyeSlash/> : <FaEye/>} 
                        />

                    </div>
                    {passwordError && <div className="error">{passwordError}</div>}
                </div>
      
                <div className="form-group">
                    <div className="password-input-container">
                        <InputComponent
                        className = "reg-input-fields"
                        type={showConfirmPassword ? "text" :"password"}
                        name="confirmPassword"
                        placeholder="Enter Password to confirm"
                        value={confirmPassword}
                        onChange={handleConfirmPasswordChange}
                        />
                    
                        <ButtonComponent
                        type = "button"
                        className = "password-toggle-button"
                        onClick={toggleConfirmPasswordVisibility}
                        text = {showConfirmPassword ? <FaEyeSlash/> : <FaEye/>} 
                        />
                    </div>
                    {confirmPasswordError && <div className="error">{confirmPasswordError}</div>}
                </div>
      
                <div className="form-group">
                    <InputComponent
                    className = "reg-input-fields"
                    type="phone"
                    name="phoneNumber"
                    pattern="[0-9]*"
                    placeholder="Enter Phone Number"
                    value={phoneNumber}
                    onChange={handlephoneChange}
                    />
                    {phoneNumberError && <div className="error">{phoneNumberError}</div>}
                </div>

                <InputComponent
                type = "Submit"
                value = "Register"
                className = "button-submit-register" 
                />

                <div className="text">
                    <h3>
                        Already our Subscriber? <Link to="/Login">Login now</Link> 
                    </h3>
                </div>
            </form>
        </div>
    );
}