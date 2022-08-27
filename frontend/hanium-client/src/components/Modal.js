import React from "react";
import "../css/Modal.css";

function Modal({ setOpenModal }) {
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
        <div className="footer">
          <input
            name="text"
            placeholder="메시지를 입력하세요."
            required
            className="message-input"
            id="message-input"
          />
          <button>전송</button>
        </div>
      </div>
    </div>
  );
}

export default Modal;
