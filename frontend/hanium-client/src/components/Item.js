import React from "react"
import "../css/Item.css"
const Item =({ itemInfo }) => {
    return (
        <div className="item-container">
            <img className="thumbnail" src={require("../img/tmp.png")} />            
            <br/>
            <p className="item-title">{itemInfo.vintageTitle}</p>
        </div>
    )

}

export default Item