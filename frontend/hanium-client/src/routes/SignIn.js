import React from "react";
import { useNavigate } from "react-router-dom";
import SignInForm from "../components/SignInForm";
import "../css/SignIn.css"
const SignIn = ({ refreshMember }) => {
    const navigate = useNavigate();
    const goBack = () => {
        navigate(-1);
    }
    return (
        <>
            <div className="signIn-container">
                <h1>로그인</h1>
                <SignInForm refreshMember={refreshMember}/>
            </div>
            <button onClick={goBack}>
                Home
            </button>
        </>
    )
}

export default SignIn;