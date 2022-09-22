import React, { useEffect, useRef } from "react";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";
import "../css/Profile.css"
import ChattingRoom from "../components/ChattingRoom";
import { useState } from "react";
const formData = new FormData();
const Profile = ({ memberObj, refreshMember }) => {
    const navigate = useNavigate();
    const [chatObj, setChatObj] = useState([]);
    const [modalOpen, setModalOpen] = useState(false);
    const [chatRoomList, setchatRoomList] = useState([]);
    const [file, setFile] = useState(null);
    const fileInput = useRef();

    const onFileChange = (event) => {
        // 이미지 입력 시 url을 읽는 함수
        const {target:{files}} = event;
        const theFile = files[0];
        const reader = new FileReader();
        reader.readAsDataURL(theFile);
        setFile(theFile);
        console.log(theFile);
    }

    const updateMemberData = async () => {
        formData.append("memberId", memberObj.memberId);
        formData.append("memberName", memberObj.memberName);
        formData.append("memberPassword", memberObj.memberPassword)
        formData.append("memberImgUrl", file);
        await axios.post(`/api/memberUpdate`, 
        formData, {headers: {
            'Content-Type': "multipart/form-data"
        }})
        .then(response => {
            console.log(response.data);
        })
        .catch(error => console.log(error.response.data))
    }
    
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
            <div className="user-info">
                <div className="user-image">
                    <img className="item-image"
                        src={memberObj.img ? (memberObj.img):(require("../img/temp.png"))}/>
                        <br/>
                        <label htmlFor="item-image">이미지 등록</label>
                        <input 
                        type="file"
                        name="item-image"
                        id="item-image"
                        onChange={onFileChange}
                        ref={fileInput}
                        />
                </div>
                <div className="user-text">
                    <div className="user-name">{memberObj?.memberName}님</div>
                </div>
            </div>
            <Link to='/vintage-upload'>중고 상품 등록</Link>
            <button onClick={updateMemberData}>회원정보수정</button>
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