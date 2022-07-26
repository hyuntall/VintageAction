import React from "react";
import {Link} from "react-router-dom"

const Navigation = () => (
    <nav>
        <ul className="navigator">
            <li>
                <Link to="/" className="Home">
                    Home
                </Link>
            </li>
            <li>
                <Link to="/sign-up" className="SignUp">
                    Sign Up
                </Link>
            </li>
            <li>
                <Link to="/sign-in" className="signIn">
                    sign In
                </Link>
            </li>
        </ul>
    </nav>
)

export default Navigation