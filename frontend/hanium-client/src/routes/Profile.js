import React from "react";

const Profile = ({ userObj }) => {
    return (
        <>
            이름: {userObj.userName}
            ID: {userObj.userId}
        </>
    )
}

export default Profile