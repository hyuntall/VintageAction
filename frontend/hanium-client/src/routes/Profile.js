import React, { useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";
import "../css/Profile.css"
import ChattingRoom from "../components/ChattingRoom";
import { useState } from "react";
const Profile = ({ memberObj, refreshMember }) => {
    const navigate = useNavigate();
    const [chatObj, setChatObj] = useState([]);
    const [modalOpen, setModalOpen] = useState(false);
    const [chatRoomList, setchatRoomList] = useState([]);
    const goBack = () => {
        navigate(-1);
    }
    // 유저 정보가 없으면 기본 페이지로 이동
    if (!memberObj) {
        navigate("/")
    }
    const SignOut = () => {
        refreshMember(null)
        navigate("/")
        axios.post('/api/members/logout/', )
        .then(response => {
            console.log(response.data);
        })
    }
    const chatList = chatRoomList && chatRoomList.map((item)=>{
        console.log(item.buyerNo.memberId,  memberObj.memberId)
        if (item.buyerNo.memberId === memberObj.memberId)
            return <button onClick={(e) => {chat(item, item.id, e)}}>{item.sellerNo.memberId}</button>
        else
            return <button onClick={(e) => {chat(item, item.id, e)}}>{item.buyerNo.memberId}</button>
      })
    const getChatRoomList = () => {
        if (memberObj) {
            axios.get(`/api/chatroom`)
            .then(response => {
                console.log(response.data)
                setchatRoomList(response.data);
            })
        } else {
            return
        }
    }
    useEffect(getChatRoomList, []);
    const chat = (chatObj, itemId, e) => {
            setChatObj(chatObj);
                setModalOpen(true);
            }
    return (
        <>
        <div className="profile-container">
            <div>
                이름: {memberObj && memberObj.memberName}
                <br/>
                ID: {memberObj && memberObj.memberId}
                <br/>
                point: {memberObj && memberObj.point}
            </div>
            <Link to='/vintage-upload'>중고 상품 등록</Link>
            <button onClick={goBack}>Home</button>
            <button onClick={SignOut}>
                sign out
            </button>
            <button onClick={chat}>채팅확인용</button>
            {chatList}
            {modalOpen && <ChattingRoom memberObj={memberObj} chatObj={chatObj} setOpenModal={setModalOpen}/>}
        </div>
        </>
    )
}

export default Profile