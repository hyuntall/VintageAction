import React, { useEffect, useState, useRef } from "react";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";
import "../css/Vintage.css"
import VintageInfo from "../components/VintageInfo";
import VintageUpdateForm from "../components/VintageUpdateForm";
const VintageDetail = ({memberObj}) => {
    const [title, setTitle] = useState("");
    const [detail, setDetail] = useState("");
    const [itemName, setItemName] = useState("");
    const [price, setPrice] = useState(0);
    const [category, setCategory] = useState("");
    const [image, setImage] = useState(null);
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
        setCategory(response.data.itemCategory);
        console.log(response.data);
    })
    }
    useEffect(getItemInfo, []);
    
    const changeMode = () => {
        if (postMode) {
            setPostMode(false);
            document.getElementById("modeButton").innerHTML = "수정"
        } else {
            setPostMode(true);
            document.getElementById("modeButton").innerHTML = "취소"
        }
    }

    

    return (
        <>
                {itemObj ? <div className="vintage-detail-container">
                    
                    {postMode ? 
                        <VintageUpdateForm vintageId={vintageId} itemInfo={itemObj}/>
                         : <VintageInfo vintageId={vintageId}/> }
                         {memberObj.memberId === itemObj.memberId ? <button id="modeButton"onClick={changeMode}>수정</button> : null}
                    </div> : null}
                    
                    
            
        </>
    )
}

export default VintageDetail