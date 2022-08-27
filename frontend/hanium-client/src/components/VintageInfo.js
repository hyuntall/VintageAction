import { React, useState, useParams } from "react";
import { useRef } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { useEffect } from "react";
import "../css/Vintage.css"
const VintageInfo =({ vintageId }) => {
    const [title, setTitle] = useState("");
    const [detail, setDetail] = useState("");
    const [itemName, setItemName] = useState("");
    const [price, setPrice] = useState(0);
    const [category, setCategory] = useState("");
    const [file, setFile] = useState(null);
    const [itemObj, setItemObj] = useState(null);

    const navigate = useNavigate();

    const getItemInfo = () => {
        axios.get(`/api/vintage/${vintageId}`)
        .then(response => {
        setItemObj(response.data);
        setTitle(response.data.title);
        setItemName(response.data.itemName);
        setPrice(response.data.itemPrice);
        setDetail(response.data.detail);
        setCategory(response.data.itemCategory);
        setFile(response.data.storeFileName[0]);
    })
    }
    useEffect(getItemInfo, []);
    return (
        <>
            {itemObj ?
                (<div className="item-info-container">
                작성자: {itemObj.memberId}
                <br/>
                제목: {title}
                <div className="item-info">
                    
                    <div className="image-info">
                        <img className="item-image"
                            src={file ? (require(`../itemImages/${file}`)):(require("../img/temp.png"))}/>
                    </div>
                    <div className="text-info">
                        카테고리: {category}
                        <br/>
                        상품명: {itemName}
                        <br/>
                        가격: {price}
                        <br/>
                        설명: {detail}
                    </div>
                </div>
            </div>) : null}
        </>
    )

}

export default VintageInfo