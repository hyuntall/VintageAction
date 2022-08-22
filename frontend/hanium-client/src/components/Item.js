import React from "react"
import "../css/Item.css"
const Item =({ itemInfo }) => {
    const itemImage = itemInfo.uploadFiles[0].storeFileName;
    return (
        <div className="item-container">
            <img className="thumbnail" 
            src={itemImage ? (require(`../itemImages/${itemImage}`)):(require("../img/temp.png"))} />            
            <br/>
            <p className="item-title">{itemInfo.vintageTitle}</p>
        </div>
    )

}

export default Item