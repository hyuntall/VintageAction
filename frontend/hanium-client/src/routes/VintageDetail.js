import React from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
const VintageDetail = () => {
    const vintageId = useParams().vintageId;
    axios.get(`/api/vintages/${vintageId}`)
        .then(response => {
        console.log(response.data)
        })
    return (
        <>
        상품 ID: {vintageId}
        </>
    )
}

export default VintageDetail