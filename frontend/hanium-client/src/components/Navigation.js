import React from "react";
import {Link, useNavigate} from "react-router-dom"
import axios from "axios";
import "../css/Navigation.css"
const Navigation = ({ isLoggedIn, memberObj, refreshMember }) => {
    const navigate = useNavigate()
    const SignOut = () => {
        refreshMember(null)
        navigate("/")
        axios.post('/api/members/logout/', )
        .then(response => {
            console.log(response.data);
        })
    }
    return (
    <nav>
        <ul className="navigator">
            <li>
                <Link to="/" className="Home">
                    Home
                </Link>
            </li>
            {memberObj ? (
            <>
                <li>
                    <Link to="/profile" className="Profile">
                        My Profile
                    </Link>
                </li>
                <li>
                    <span className="Home" onClick={SignOut}>
                        Sign Out
                    </span>
                </li>
            </>
            ) : (
                <>
                <li>
                    <Link to="/sign-up" className="SignUp">
                        Sign Up
                    </Link>
                </li>
                <li>
                    <Link to="/sign-in" className="SignIn">
                        Sign In
                    </Link>
                </li>
            </>
            )
            
            }
        </ul>
    </nav>
)}

export default Navigation