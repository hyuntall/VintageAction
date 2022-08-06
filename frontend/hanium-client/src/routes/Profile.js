import React from "react";
import { Link, useLocation, useNavigate } from "react-router-dom";
import axios from "axios";
const Profile = ({ memberObj, refreshMember }) => {
    const navigate = useNavigate();
    const location = useLocation();
    const goBack = () => {
        navigate(-1);
    }
    if (!memberObj) {
        navigate("/")
    }
    const SignOut = () => {
        refreshMember(null)
        navigate("/")
        axios.post('/api/members/logout/', )
        .then(response => {
            console.log(response.data);
        })
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