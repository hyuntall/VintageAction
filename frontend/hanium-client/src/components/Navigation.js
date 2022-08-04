import React from "react";
import {Link} from "react-router-dom"
import "../css/Navigation.css"
const Navigation = ({ isLoggedIn, memberObj }) => {

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
                    <Link to="/" className="Home" >
                        Sign Out
                    </Link>
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