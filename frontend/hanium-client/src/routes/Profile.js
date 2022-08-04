import React from "react";
import { Link, useLocation, useNavigate } from "react-router-dom";

const Profile = ({ memberObj, refreshMember }) => {
    const navigate = useNavigate();
    const location = useLocation();
    const goBack = () => {
        navigate(-1);
    }
    const SignOut = () => {
        refreshMember(null)
        navigate("/")
    }
    return (
        <>
            <div>
                이름: {memberObj.memberName}
            </div>
            <div>
                ID: {memberObj.memberId}
            </div>
            <Link to='/vintage-upload'>중고 상품 등록</Link>
            <button onClick={goBack}>Home</button>
            <button onClick={SignOut}>
                sign out
            </button>
        </>
    )
}

export default Profile