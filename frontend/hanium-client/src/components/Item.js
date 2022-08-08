import React from "react"
import "../css/Item.css"

const Item =({ itemName }) => {

    return (
        <div className="item-container">
            <img className="thumbnail" src={require("../img/tmp.png")} />
            <br/>
            <p className="item-title">{itemName}</p>
        </div>
    )

}

export default Item