import React from "react";
import { useParams } from "react-router-dom";

const VintageInfo = () => {
    const vintageId = useParams().vintageId;
    console.log(vintageId);
    return (
        <>
        상품 ID: {vintageId}
        </>
    )
}

export default VintageInfo