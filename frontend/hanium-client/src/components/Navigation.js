import React from "react";
import {Link} from "react-router-dom"

const Navigation = () => (
    <nav>
        <ul className="navigator">
            <li>
                <Link to="/sign-up" className="SignUp">
                    Sign Up
                </Link>
            </li>
            <li>
                <Link to="/home" className="Home">
                    Home
                </Link>
            </li>
        </ul>
    </nav>
)

export default Navigation