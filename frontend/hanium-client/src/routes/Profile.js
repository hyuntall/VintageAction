import React from "react";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";
import "../css/Profile.css"
const Profile = ({ memberObj, refreshMember }) => {
    const navigate = useNavigate();
    const goBack = () => {
        navigate(-1);
    }
    // 유저 정보가 없으면 기본 페이지로 이동
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
        <div className="profile-container">
            <div>
                이름: {memberObj.memberName}
                <br/>
                ID: {memberObj.memberId}
                <br/>
                point: {memberObj.point}
            </div>
            <Link to='/vintage-upload'>중고 상품 등록</Link>
            <button onClick={goBack}>Home</button>
            <button onClick={SignOut}>
                sign out
            </button>
        </div>
        </>
    )
}

export default Profile