import React from "react";
import { Route } from "react-router-dom";

const Home = ({ isLoggedIn, userObj }) => {
    console.log(isLoggedIn)
    if(isLoggedIn) {
        console.log(userObj.userName);
    }
    return (
        <>
            메인화면입니다.
        </>
    )
}

export default Home