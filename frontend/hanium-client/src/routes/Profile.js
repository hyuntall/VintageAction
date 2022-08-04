import React from "react";
import { Link, useLocation, useNavigate } from "react-router-dom";

const Profile = ({ memberObj }) => {
    const navigate = useNavigate();
    const location = useLocation();
    const goBack = () => {
        navigate(-1);
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
        </>
    )
}

export default Profile