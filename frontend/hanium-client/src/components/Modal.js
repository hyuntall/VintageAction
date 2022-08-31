import React, { useEffect, useRef, useState } from "react";
import "../css/Modal.css";
import * as StompJs from "@stomp/stompjs";
import * as SockJS from "sockjs-client";
function Modal({ chatObj, setOpenModal }) {
  const client = useRef({});
  const [message, setMessage] = useState("");
  const [chatMessages, setChatMessages] = useState([]);

  useEffect(() => {
    connect();
    console.log(client.current.connected)
    return () => disconnect();
  }, []);
  const connect = () => {
    client.current = new StompJs.Client({
      brokerURL: 'ws://localhost:8080/ws',
      onConnect: () => {
        subscribe();
      },
    })
  }
  const onChange = (event) => {
    setMessage(event.target.value);
  }

  const disconnect = () => {
    client.current.deactivate();
  };
  const subscribe = () => {
    client.current.subscribe(`/room/chat/${0}`, ({ body }) => {
      setChatMessages((_chatMessages) => [..._chatMessages, JSON.parse(body)]);
    });
  };

  const publish = (event) => {
    event.preventDefault();
    if (!client.current.connected) {
      return;
    }

    client.current.publish({
      destination: "/app/api/chat",
      body: JSON.stringify({ roomSeq: 0, message }),
    });

    setMessage("");
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
      <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    </div>
  );
}

export default Modal;
