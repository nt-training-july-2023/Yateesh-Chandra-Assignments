import React from "react";
import "./HomePage.css";
import { useNavigate } from "react-router-dom";

function HomePage(){
    const navigate = useNavigate();
    const handleButton = () => {
        navigate("/register");
    }
    return(
        <div className="get-started">
      <header className="header">
        <h1>Welcome to the Assessment Platform</h1>
        <p>Get started to begin your learning journey!</p>
      </header>
      <main className="content">
        <section className="steps">
          <div className="step">
            <h2>Step 1</h2>
            <p>Sign up for an account</p>
          </div>
          <div className="step">
            <h2>Step 2</h2>
            <p>Explore available assessments</p>
          </div>
          <div className="step">
            <h2>Step 3</h2>
            <p>Take assessments and track your progress</p>
          </div>
        </section>
        <section className="cta">
          <h2>What are you yet waiting for?</h2>
          <button className="get-started-button" onClick={handleButton}>Get Started</button>
        </section>
      </main>
    </div>
  );
}

export default HomePage;