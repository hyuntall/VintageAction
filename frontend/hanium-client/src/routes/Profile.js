import React from "react";
import { useLocation, useNavigate } from "react-router-dom";

const Profile = ({ userObj }) => {
    const navigate = useNavigate();
    const location = useLocation();
    const goBack = () => {
        navigate(-1);
    }
    return (
        <>
            <div>
                이름: {location.state.userObj.name}
            </div>
            <div>
                ID: {location.state.userObj.id}
            </div>
            <button onClick={goBack}>Home</button>
        </>
    )
}

export default Profile