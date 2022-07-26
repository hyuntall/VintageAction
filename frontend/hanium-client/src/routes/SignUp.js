import SignUpForm from "../components/SignUpForm";
import React from "react";
import { Link } from "react-router-dom";
const SignUp = () => {

    return (
        <>
            <div className="authContainer">
                <SignUpForm/>
            </div>
        </>
    )
}

export default SignUp;