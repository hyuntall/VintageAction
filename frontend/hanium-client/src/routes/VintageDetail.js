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
        setImageUrl(response.data.itemImages[0].storeFileName);
        console.log(response.data.itemImages[0].storeFileName);

        })
    }
    useEffect(getItemInfo, []);
    
    return (
        <>
            <div className="vintage-detail-container">
                <div className="item-info">
                    <div className="image-info">
                        <img className="item-image"
                            src={imageUrl ? (require(`../img/${imageUrl}`)):(require("../img/temp.png"))}/>
                    </div>
                    <div className="text-info">
                        <label htmlFor="title-input">상품명</label>
                        <input
                        value={itemName}
                        readOnly/>
                        <br/>
                        <label htmlFor="title-input">가격</label>
                        <input
                        value={price}
                        readOnly/>
                        <br/>
                        <label htmlFor="title-input">설명</label>
                        <input
                        value={detail}
                        readOnly/>
                    </div>
                </div>

            </div>
        </>
    )
}

export default VintageDetail