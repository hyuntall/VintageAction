import React from "react";
import { useNavigate } from "react-router-dom";

const Home = () => {
    const navigate = useNavigate();
    navigate('/vintages')
    return (
        <>
            메인화면입니다.
        </>
    )
}

export default Home