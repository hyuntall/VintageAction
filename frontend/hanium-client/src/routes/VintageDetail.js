import React, { useEffect, useState, useRef } from "react";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";
import "../css/Vintage.css"
const VintageDetail = ({memberObj}) => {
    const [title, setTitle] = useState("");
    const [detail, setDetail] = useState("");
    const [itemName, setItemName] = useState("");
    const [price, setPrice] = useState(0);
    const [file, setFile] = useState(null);
    const [itemObj, setItemObj] = useState(null);
    const [postMode, setPostMode] = useState(false);
    const vintageId = useParams().vintageId;
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
        console.log(response.data);
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

    const onFileChange = (event) => {
        // 이미지 입력 시 url을 읽는 함수
        const {target:{files}} = event;
        const theFile = files[0];
        const reader = new FileReader();
        reader.readAsDataURL(theFile);
        setFile(theFile);
        console.log(theFile);
    }

    const onChange = (event) => {
        const {target: {name, value}} = event;
        
        if(name === "title"){
            setTitle(value)
        }else if(name == "detail"){
            setDetail(value)
        }else if(name === "item-name"){
            setItemName(value)
        }else if(name === "price"){
            setPrice(value)
        }
    }

    const editPost = async (event) => {
        console.log("gd")
        
        // 중고 상품을 업로드하는 함수
        event.preventDefault();
        // 입력받은 데이터를 객체에 담아
        // 회원가입 api에 post 요청
        formData.append("imageFiles", file);
        formData.append("vintageTitle", title);
        formData.append("itemName", itemName);
        formData.append("itemPrice", Number(price));
        formData.append("vintageDetail", detail);

        await axios.post(`/api/vintage/${vintageId}/edit`, 
        formData, {headers: {
            'Content-Type': "multipart/form-data"
        }})
        .then(response => {
            console.log(response.data);
            navigate(`/vintage`);
        })
        .catch(error => console.log(error.response.data))
    };

    return (
        <>
            {itemObj ?
                (<div className="vintage-detail-container">
                {memberObj.memberId === itemObj.memberId ? 
                <div>
                    <button onClick={changeMode}>수정</button>
                    
                    {postMode ? <div>
                        <input 
                        type="file"
                        name="item-image"
                        id="item-image"
                        onChange={onFileChange}
                        ref={fileInput}
                        />
                        <button onClick={editPost}>저장</button></div> : null}
                    </div> : null}
                <div className="item-info">
                    <input
                    className="readOnly"
                    value={title}
                    name="title"
                    readOnly
                    onChange={onChange}/>
                    <div className="image-info">
                        <img className="item-image"
                            src={itemObj.itemImages[0].storeFileName ? (require(`../itemImages/${itemObj.itemImages[0].storeFileName}`)):(require("../img/temp.png"))}/>
                    </div>
                    <div className="text-info">
                        <label htmlFor="title-input">상품명</label>
                        <input
                        className="readOnly"
                        value={itemName}
                        name="item-name"
                        readOnly
                        onChange={onChange}/>
                        <br/>
                        <label htmlFor="title-input">가격</label>
                        <input
                        className="readOnly"
                        value={price}
                        name="price"
                        readOnly
                        onChange={onChange}/>
                        <br/>
                        <label htmlFor="title-input">설명</label>
                        <input
                        className="readOnly"
                        value={detail}
                        name="detail"
                        readOnly
                        onChange={onChange}/>
                    </div>
                </div>

            </div>) : null}
        </>
    )
}

export default VintageDetail