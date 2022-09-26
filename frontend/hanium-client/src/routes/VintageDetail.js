import React, { useEffect, useState, useRef, memo } from "react";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";
import "../css/Vintage.css"
import VintageInfo from "../components/VintageInfo";
import VintageUpdateForm from "../components/VintageUpdateForm";
import ChattingRoom from "../components/ChattingRoom";

const VintageDetail = ({memberObj, refreshMember}) => {
    const [itemObj, setItemObj] = useState(null);
    const [postMode, setPostMode] = useState(false);
    const [modalOpen, setModalOpen] = useState(false);
    const [chatObj, setChatObj] = useState(null);
    const vintageId = useParams().vintageId;
    let memObj = memberObj
    const getItemInfo = () => {
        axios.get(`/api/vintage/${vintageId}`)
        .then(response => {
        setItemObj(response.data);
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

    const deal = () => {
        if(!memberObj){
            alert("로그인이 필요합니다.")
            return
        }
        if (memObj.point < itemObj.itemPrice){
            alert("가격이 부족합니다.")
            return
        }

        if(window.confirm("구매하시겠습니까?")){
            axios.post(`/api/vintage/deal?vintageBoardId=${vintageId}`)
            .then(response => {
                console.log(response.data);
                memObj.point = memObj.point - itemObj.itemPrice;
                refreshMember(memObj);
                alert("구매가 완료되었습니다.");
            }).catch(error => {
                alert(error.response.data);
            })
        } else{
            return
        }
    }
    const chat =() => {
        if (memberObj) {
            axios.post(`/api/chat/new?receiverNo=${itemObj.memberNo}&itemId=${vintageId}`)
            .then(response => {
                setChatObj(response.data)
                console.log(response.data)
                setModalOpen(true);
            })
        } else {
            alert("로그인이 필요합니다.")
        }
    }

    return (
        <>
                {itemObj ? <div className="vintage-detail-container">
                    {postMode ? 
                        <VintageUpdateForm vintageId={vintageId} itemInfo={itemObj}/>
                         : <VintageInfo vintageId={vintageId}/> }
                    {memberObj?.memberId === itemObj.memberId ? 
                    <button id="modeButton"onClick={changeMode}>수정</button> : 
                    <div>
                        <button onClick={deal}>구매</button>
                        <button className="openModalBtn" onClick={chat}>
                            채팅
                        </button>
                        {modalOpen && <ChattingRoom memberObj={memberObj} deal={deal}chatObj={chatObj} setOpenModal={setModalOpen}/>}
                    </div>}
                    </div> : null}
                    
                    
            
        </>
    )
}

export default VintageDetail