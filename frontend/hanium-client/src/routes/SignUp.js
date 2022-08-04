import SignUpForm from "../components/SignUpForm";
import React from "react";
import { Link, useNavigate } from "react-router-dom";
const SignUp = ({refreshMember}) => {
    const navigate = useNavigate();
    const goBack = () => {
        navigate(-1);
    }
    return (
        <>
            <div className="authContainer">
                <SignUpForm refreshMember={refreshMember}/>
            </div>
            <button onClick={goBack}>
                Home
            </button>
        </>
    )
}

export default SignUp;