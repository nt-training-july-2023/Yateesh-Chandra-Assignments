import styles from  "../css/HomePage.module.css"
import NTLogo from "../img/NTLogo.png"

function Header(){
    return(
        <header className={styles.header}>
                <nav className={styles.navbar}>
                    <div className={styles.container}>
                        <div className={styles.logo}>
                            ASSESSMENT PLATFORM
                            
                        </div>
                    </div>
                    <div className={styles.right}>
                            <img src= {NTLogo} alt = "logo" width ="60px" height="60px"/>
                            </div>                                                   
                </nav>
            </header>
    );
};

export default Header;
