import React from "react";
import {Link} from "react-router-dom"

const Navigation = ({ isLoggedIn, userObj }) => {
    console.log(userObj)
    return (
    <nav>
        <ul className="navigator">
            <li>
                <Link to="/" state={{isLoggedIn: isLoggedIn, userObj: userObj}} className="Home">
                    Home
                </Link>
            </li>
            {isLoggedIn ? (
            <>
                <li>
                    <Link to="/profile" state={{isLoggedIn: isLoggedIn, userObj: userObj}} className="Profile">
                        My Profile
                    </Link>
                </li>
                <li>
                    <Link to="/" state={{isLoggedIn: false, userObj: null}} className="Home">
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