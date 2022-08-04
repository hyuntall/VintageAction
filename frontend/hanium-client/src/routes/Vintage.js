import SignUpForm from "../components/SignUpForm";
import React from "react";
import axios from "axios";

const Vintage = () => {
    axios.get('/api/vintages/')
    .then(response => {
        console.log(response.data);
    })
    return (
        <div className="vintageContainer">
            중고 상품 리스트
        </div>
    )
}

export default Vintage;