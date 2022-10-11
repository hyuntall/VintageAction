import React from "react"
import "../css/Item.css"
const Item =({ itemInfo }) => {
    const itemImage = itemInfo.storeFileNames[0];
    return (
        <div className="item-container">
            <img className="thumbnail" 
            src={itemImage ? (require(`../itemImages/${itemImage}`)):(require("../img/temp.png"))} />            
            <br/>
            <p className="item-title">{itemInfo.vintageTitle}</p>
            <p className="item-time">00:00:00</p>
        </div>
    )

}

export default Item