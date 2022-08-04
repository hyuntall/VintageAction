import React from "react";
import { useNavigate } from "react-router-dom";
import SignInForm from "../components/SignInForm";

const SignIn = ({ refreshMember }) => {
    const navigate = useNavigate();
    const goBack = () => {
        navigate(-1);
    }
    return (
        <>
            <div className="authContainer">
                <SignInForm refreshMember={refreshMember}/>
            </div>
            <button onClick={goBack}>
                Home
            </button>
        </>
    )
}

export default SignIn;