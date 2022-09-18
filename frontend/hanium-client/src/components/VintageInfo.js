import { React, useState, useParams } from "react";
import { useRef } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { useEffect } from "react";
import "../css/Vintage.css";
const VintageInfo = ({ vintageId }) => {
  const [title, setTitle] = useState("");
  const [detail, setDetail] = useState("");
  const [itemName, setItemName] = useState("");
  const [price, setPrice] = useState(0);
  const [category, setCategory] = useState("");
  const [file, setFile] = useState(null);
  const [itemObj, setItemObj] = useState(null);
  const [postMode, setPostMode] = useState(false);

  const formData = new FormData();
  const fileInput = useRef();
  const navigate = useNavigate();

  const getItemInfo = () => {
    axios.get(`/api/vintage/${vintageId}`).then((response) => {
      setItemObj(response.data);
      setTitle(response.data.title);
      setItemName(response.data.itemName);
      setPrice(response.data.itemPrice);
      setDetail(response.data.detail);
      setCategory(response.data.itemCategory);
      setFile(response.data.storeFileName[0]);
    });
  };
  useEffect(getItemInfo, []);

  const changeMode = () => {
    setPostMode(true);
    var x = document.getElementsByClassName("readOnly");
    for (var i = 0; i < x.length; i++) {
      x[i].readOnly = false;
    }
  };

  const deal = () => {
    axios
      .post(`/api/vintage/deal`, {
        vintageBoardId: vintageId,
      })
      .then((response) => {
        console.log(response.data);
      });
  };
  return (
    <>
      {itemObj ? (
        <div className="item-info-container">
          <input
            className="category-detail"
            value={category}
            name="price"
            readOnly
          />
          <input
            className="content-title"
            value={title}
            name="title"
            readOnly
          />
          <div className="item-info">
            <div className="image-info">
              <img
                className="item-image"
                src={
                  file
                    ? require(`../itemImages/${file}`)
                    : require("../img/temp.png")
                }
              />
            </div>
            <div className="text-info">
              <label htmlFor="title-input" className="detail">
                상품명
              </label>
              <input
                className="detail-input1"
                value={itemName}
                name="item-name"
                readOnly
              />
              <br />
              <label htmlFor="title-input" className="detail">
                가격
              </label>
              <input
                className="detail-input2"
                value={price + " 원"}
                name="price"
                readOnly
              />
              <br />
              <label htmlFor="title-input" className="detail">
                설명
              </label>
              <input
                className="detail-input3"
                value={detail}
                name="detail"
                readOnly
              />
            </div>
          </div>
        </div>
      ) : null}
    </>
  );
};

export default VintageInfo;
