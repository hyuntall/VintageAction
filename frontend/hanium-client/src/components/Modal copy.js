import React, { useEffect, useState } from "react";
import "../css/Modal.css";
import StompJs, { Stomp } from "@stomp/stompjs";
import SockJS from "sockjs-client";

function Modal({ chatObj, setOpenModal }) {
  const [message, setMessage] = useState("");

  useEffect(() => {
    const script = document.createElement("script");
    script.src = "https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js";
    script.async = true;
    document.body.appendChild(script);
    const script2 = document.createElement("script");
    script2.src = "https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js";
    script2.async = true;
    document.body.appendChild(script2);
    if(chatObj) {
      var socket = new SockJS('/ws');
      console.log(socket);
      stompClient = Stomp.over(socket);
      stompClient.connect({}, onConnected);
      console.log(stompClient);
    }
  }, []);
  var stompClient = null;

  const onMessageReceived = (payload) => {
    //구독한 destination으로 수신한 메시지 파싱
    var message = JSON.parse(payload.body);

    
}
  const onConnected = () => {
    // user 개인 구독
    //stompClient.subscribe('/room/' + chatObj.chatRoom + '/queue/messages', onMessageReceived);
  }
  const sendMessage = (event) => {
    
    event.preventDefault();
    
    var messageContent = message.trim();
    if(messageContent && stompClient) {
        var chatMessage = {
            sender: chatObj.sender,
            receiver: chatObj.receiver,
            content: message,
            chatroom: chatObj.chatRoom,
            type: 'CHAT'
        };
        console.log("gd")
        console.log(chatMessage)
        stompClient.send('/room/'+chatObj.chatRoom+'/queue/messages', {}, JSON.stringify(chatMessage)); //json 직렬화해서 보내기
        stompClient.send('/app/chat', {}, JSON.stringify({'content': message,'senderId':chatObj.sender,
            'receiverId': chatObj.receiver, 'chatroomId': chatObj.chatRoom}));
        setMessage("");
    }

  }

  const onChange =(event) => {
    const {target: {name, value}} = event;
    setMessage(value);
  }

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
        <div className="title">
          <div className="profile">
            <img src={require("../img/icon1.png")} className="proimg" />
            <h3>닉네임</h3>
            <h3>아이디</h3>
          </div>
        </div>
        <div className="body">
          <div>채팅1</div>
          <br />
          <div>채팅2</div>
        </div>
        <form className="footer" onSubmit={sendMessage}>
          <input
            name="text"
            placeholder="메시지를 입력하세요."
            required
            className="message-input"
            id="message-input"
            value={message}
            onChange={onChange}
          />
          <button type="submit">전송</button>
        </form>
      </div>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    </div>
  );
}

export default Modal;
