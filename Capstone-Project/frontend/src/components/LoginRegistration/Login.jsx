import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

function Login(){
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [emailError, setEmailError] = useState("");
    const [passwordError, setPasswordError] = useState("");
    const navigate = useNavigate();

    const handleEmailChange = (e) => {
        setEmail(e.target.value);
        if(!email){
            setEmailError("");
            console.log("Email is required");
        }
        else{
            setEmailError("")
            console.log("Email is entered.!");
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
            console.log("Password is entered..!");
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
              navigate("/admin");
              console.log("You are navigated to Admin dashboard..!");
            } else {
              setEmailError("Unable to access..!");
              console.log("You have no access to the website");
            }
          } else {
            setEmailError("Incorrect email or Password");
            console.log("Detected mistake in the email ID or Password..!");
          }
        } catch (err) {
          setEmailError("An error occurred during login.");
          console.log(err);
        }
      };
      

    return(
        <div className = "wrapper">
            <h1>Login</h1>
            <form onSubmit={handleFormSubmit}>
                <div className = "input-box">
                    <input type = "email" id = "email" placeholder="Enter Email" value = {email} onChange={handleEmailChange}/>
                    {emailError && <small>{emailError}</small>}
                </div>
                            
                <div className = "input-box">
                    <input type = "password" id = "password" placeholder="Enter Password" value = {password} onChange={handlePasswordChange}/>
                    {passwordError && <small>{passwordError}</small>}
                </div>

                <div className="input-box button-cont">
                    <input type="Submit" value="Login" />
                    <input type="button" value="Home" onClick={() => navigate("/")}/>
                </div>
                            
                <div className="input-label">
                    <label>Are you new Subscriber</label>
                </div>

                <div className="input-box button">
                    <input type="button" value="New User" onClick={() => navigate("/register")}/>
                </div>                                                       
            </form>
        </div>
                
    );
}

export default Login;