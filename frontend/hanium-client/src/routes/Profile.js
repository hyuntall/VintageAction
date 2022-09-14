import React from "react";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";
import "../css/Profile.css"
import ChattingRoom from "../components/ChattingRoom";
import { useState } from "react";
const Profile = ({ memberObj, refreshMember }) => {
    const navigate = useNavigate();
    const [chatObj, setChatObj] = useState([]);
    const [modalOpen, setModalOpen] = useState(false);
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
    const click =() => {
        if (memberObj) {
            axios.post(`/api/chat/new?receiverNo=${2}&itemId=${1}`)
            .then(response => {
                setChatObj(response.data)
                console.log(response.data)
                setChatObj((prevState) => ({
                    ...prevState,
                    id: 1
                }))
                setModalOpen(true);
            })
        } else {
            alert("로그인이 필요합니다.")
        }
    }
    return (
        <>
        <div className="profile-container">
            <div>
                이름: {memberObj.memberName}
                <br/>
                ID: {memberObj.memberId}
                <br/>
                point: {memberObj.point}
            </div>
            <Link to='/vintage-upload'>중고 상품 등록</Link>
            <button onClick={goBack}>Home</button>
            <button onClick={SignOut}>
                sign out
            </button>
            <button onClick={click}>채팅확인용</button>
            {modalOpen && <ChattingRoom chatObj={chatObj} setOpenModal={setModalOpen}/>}
        </div>
        </>
    )
}

export default Profile