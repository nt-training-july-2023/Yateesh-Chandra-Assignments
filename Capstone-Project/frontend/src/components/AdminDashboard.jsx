import { useNavigate } from "react-router-dom";
import "./AdminDashStyle.css"

function AdminDashboard(){

    const navigate = useNavigate();
    return(
        <div className="header">
            <h1>Welcome to Admin Dash board</h1>
            <div className="input-box button">
                <input type="button" value="Go to categories" onClick={() => navigate("/categories")}/>
            </div>                                                       
        </div>
    );
}

export default AdminDashboard;