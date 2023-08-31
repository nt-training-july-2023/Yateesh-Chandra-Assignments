import React from "react";
import { useNavigate } from "react-router-dom";
import styles from "../css/HomePage.module.css"; // Import your custom CSS for styling
import Header from "./Header";

function HomePage() {

    const navigate = useNavigate();

    return (
        <div className="home-page">
            <Header/>
            <main className="main-content">
                <section className={styles.hero}>
                    <div className={styles.container}>
                        <h1>Welcome to the Assessment Platform</h1>
                        <p>Empowering learners through interactive assessments.</p>
                        <button type = "button" className={styles.ctaButton} onClick={() => navigate("/login")}>
                            Get Started
                        </button>
                    </div>
                </section>
                <section className={styles.features}>
                    <div className={styles.container}>
                        <div className={styles.featureList}>
                            <div className={styles.feature}>
                                <h3>Interactive Quizzes</h3>
                                <p>Engage learners with interactive quizzes and assessments.</p>
                            </div>
                            <div className={styles.feature}>
                                <h3>Customizable Content</h3>
                                <p>Create and customize assessment content according to your needs.</p>
                            </div>
                            <div className={styles.feature}>
                                <h3>Progress Tracking</h3>
                                <p>Track learner progress and performance over time.</p>
                            </div>
                        </div>
                    </div>
                </section>
            </main>
            <footer className={styles.footer}>
                <div className={styles.container}>
                    <p>&copy; 2023 Assessment Platform. All rights reserved.</p>
                </div>
            </footer>
        </div>
    );
}

export default HomePage;
