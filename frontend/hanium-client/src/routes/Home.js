import React from "react";
import { Route, useLocation } from "react-router-dom";
import Navigation from "../components/Navigation";

const Home = () => {
    const location = useLocation();
    if (location.state){
        if(location.state.isLoggedIn) {
            console.log("로케이션", location.state);
        }
    }
    return (
        <>
            메인화면입니다.
        </>
    )
}

export default Home