import React, { useEffect, useRef, useState } from "react";
import "../css/Modal.css";
import SockJS from "sockjs-client";
import { Stomp } from "@stomp/stompjs";
import axios from "axios";
import Message from "./Message";

function ChattingRoom({ memberObj, deal, chatObj, setOpenModal }) {
  let stompClient = useRef({});
  const [message, setMessage] = useState("");
  const [chatMessages, setChatMessages] = useState([]);
  const scrollRef = useRef();

  useEffect(() => {
    connect();
    enterChatRoom();
    return () => stompClient.current.disconnect();
  }, []);

  const scrollToBottom = () => {
    const element = document.getElementById("bottom-reference");
    element.scrollIntoView({behavior: "smooth"})
    //scrollRef.current.scrollTop = scrollRef.current.scrollHeight;
  }

  const chatMessage = chatMessages && chatMessages.map((m)=>{
    if (m.senderId == memberObj.memberNo || m.sender == memberObj.memberNo)
      return <li className="chat me"><Message messageObj={m}/></li>
    else
      return <li className="chat other"><Message messageObj={m}/></li>
  })

  const connect = (event) => {
  
    if(1) {//'ws://localhost:8080/ws'
      var socket = new SockJS('http://localhost:8080/ws');
      stompClient.current = Stomp.over(socket);
      stompClient.current.connect({}, () => {
        setTimeout(function() {
          onConnected()
        }, 500)
      })
    }
  
}

function onConnected() {
  // user 개인 구독
  stompClient.current.subscribe('/room/' + chatObj.id + '/queue/messages', onMessageReceived);
}



const sendMessage = (event) => {
  //event.preventDefault();
  var messageContent = message.trim();
  if (chatObj.sellerNo.memberNo == memberObj.memberNo)
    var otherNo = chatObj.buyerNo.memberNo;
  else
    var otherNo = chatObj.sellerNo.memberNo;
  if(messageContent && stompClient) {
      var chatMessage = {
          sender: memberObj.memberNo,
          receiver: otherNo,
          content: message,
          chatroom: chatObj.id,
          type: 'CHAT'
      };
      stompClient.current.send('/room/'+chatObj.id+'/queue/messages', {}, JSON.stringify(chatMessage)); //json 직렬화해서 보내기
      stompClient.current.send('/app/chat', {}, JSON.stringify({'content': message,'senderId':chatMessage.sender,
          'receiverId': chatMessage.receiver, 'chatroomId': chatObj.id}));
      console.log("메시지 전송 완료");
      setMessage("");
  }


}


const onMessageReceived = (payload) => {
  //구독한 destination으로 수신한 메시지 파싱
  var newMessage = JSON.parse(payload.body);
  if (newMessage){
    console.log("받았음: ", newMessage);
    setChatMessages((chatMessages) => {
      return [...chatMessages, newMessage];
    })
  }
  scrollToBottom();
}



  const enterChatRoom = async () => {
    if (chatObj) {
      await axios.get(`/api/chat/${chatObj.id}`)
      .then(response => {
          setChatMessages(response.data);
          console.log(chatMessages);
          scrollToBottom();
      })
    } else {
      alert("로그인이 필요합니다.")
    }
  }
  
  const onChange = (event) => {
    setMessage(event.target.value);
  }

  const publish = (event) => {
    event.preventDefault();
    if (!stompClient.current.connected || !message) 
      return;
    sendMessage();
    scrollToBottom();
  };
  return (
    <div className="modalBackground">
      <div className="modalContainer">
        <div className="titleCloseBtn">
          <button
            onClick={() => {
              setOpenModal(false);
            }}
          >
            X
          </button>
        </div>
        <div className="chat-title">
        
        </div>
        <div className="body">
          <ul className="messageList">
          {chatMessage}
          <div id="bottom-reference" ref={scrollRef}></div>
          </ul>
        </div>
        <button className="deal-button" onClick={deal}>구매</button>
        <form className="footer">
          <input
            name="text"
            placeholder="메시지를 입력하세요."
            required
            className="message-input"
            id="message-input"
            value={message}
            onChange={onChange}
          />
          <button onClick={publish}>전송</button>
        </form>
      </div>
    </div>
  );
}

export default ChattingRoom;
