import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import "../css/Vintage.css"

const VintageDetail = () => {
    const [itemName, setItemName] = useState("");
    const [price, setPrice] = useState(0);
    const [detail, setDetail] = useState("");
    const [imageUrl, setImageUrl] = useState(null);
    const [images, setImages] = useState(null);
    
    const vintageId = useParams().vintageId;
    const getItemInfo = () => {
        axios.get(`/api/vintage/${vintageId}`)
        .then(response => {
        console.log(response.data)
        setItemName(response.data.itemName);
        setPrice(response.data.itemPrice);
        setDetail(response.data.detail);
        setImages(response.data.itemImages);
        setImageUrl(response.data.itemImages[0].fullPath);
        console.log(response.data.itemImages[0].fullPath);

        })
    }
    useEffect(getItemInfo, []);
    
    return (
        <>
            상품 ID: {vintageId}
            <div className="vintage-detail-container">
                <div className="item-info">
                    <div className="image-info">
                        <img className="item-image"
                            src={imageUrl ? (imageUrl):(require("../img/temp.png"))}/>
                    </div>
                    <div className="text-info">
                        <label htmlFor="title-input">상품명</label>
                        <span>
                            {itemName}
                        </span>
                        <br/>
                        <label htmlFor="title-input">가격</label>
                        <span>
                            {price}
                        </span>
                        <br/>
                        <label htmlFor="title-input">설명</label>
                        <span>
                            {detail}
                        </span>
                    </div>
                </div>

            </div>
        </>
    )
}

export default VintageDetail