import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import "../css/Vintage.css"
import AuctionInfo from "../components/AuctionInfo";
import VintageUpdateForm from "../components/VintageUpdateForm";
import ChattingRoom from "../components/ChattingRoom";

const AuctionDetail = ({memberObj, refreshMember}) => {
    const [itemObj, setItemObj] = useState(null);
    const [postMode, setPostMode] = useState(false);
    const [modalOpen, setModalOpen] = useState(false);
    const [chatObj, setChatObj] = useState(null);
    const auctionId = useParams().auctionId;

    const getItemInfo = () => {
        axios.get(`/api/auction/${auctionId}`)
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

    const chat =() => {
        if (memberObj) {
            axios.post(`/api/chat/new?receiverNo=${itemObj.memberNo}&itemId=${auctionId}`)
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
                {itemObj ? <div className="auction-detail-container">
                    {postMode ? 
                        <VintageUpdateForm auctionId={auctionId} itemInfo={itemObj}/>
                         : <AuctionInfo auctionId={auctionId} memberObj={memberObj}/> }
                    {memberObj?.memberId === itemObj.memberId ? 
                    <button id="modeButton"onClick={changeMode}>수정</button> : null}
                    </div> : null}
                    
                    
            
        </>
    )
}

export default AuctionDetail