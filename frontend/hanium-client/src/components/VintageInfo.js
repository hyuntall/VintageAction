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
    const [postMode, setPostMode] = useState(false);

    const formData = new FormData();
    const fileInput = useRef();
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
    
    const changeMode = () => {
        setPostMode(true);
        var x = document.getElementsByClassName("readOnly");
        for (var i = 0; i < x.length; i++) {
            x[i].readOnly = false;
        }
    }


    const deal = () => {
        axios.post(`/api/vintage/deal`, 
        {
            "vintageBoardId": vintageId,
        })
        .then(response => {
        console.log(response.data);
    })
    }
    return (
        <>
            {itemObj ?
                (<div className="item-info-container">
                <button onClick={deal}>
                    구매
                </button>
                <input
                    className="title readOnly"
                    value={title}
                    name="title"
                    readOnly/>
                <div className="item-info">
                    
                    <div className="image-info">
                        <img className="item-image"
                            src={file ? (require(`../itemImages/${file}`)):(require("../img/temp.png"))}/>
                    </div>
                    <div className="text-info">
                        <label htmlFor="category">카테고리</label>
                        <select name="category" id="category">
                            <option >category</option>
                            <option value="clothes">의류</option>
                            <option value="test1">test1</option>
                            <option value="test2">test2</option>
                            <option value="test3">test3</option>
                            <option value="test4">test4</option>
                            <option value="test5">test5</option>
                        </select>
                        <br/>
                        <label htmlFor="title-input">상품명</label>
                        <input
                        className="readOnly"
                        value={itemName}
                        name="item-name"
                        readOnly/>
                        <br/>
                        <label htmlFor="title-input">가격</label>
                        <input
                        className="readOnly"
                        value={price}
                        name="price"
                        readOnly/>
                        <br/>
                        <label htmlFor="title-input">설명</label>
                        <input
                        className="readOnly"
                        value={detail}
                        name="detail"
                        readOnly/>
                    </div>
                </div>
            </div>) : null}
        </>
    )

}

export default VintageInfo