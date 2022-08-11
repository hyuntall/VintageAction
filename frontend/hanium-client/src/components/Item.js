import React from "react"
import "../css/Item.css"
import { Link } from "react-router-dom"
const Item =({ itemInfo }) => {
    return (
        <Link to={`/vintage/${itemInfo.vintageId}`}>
        <div className="item-container">
            <img className="thumbnail" src={require("../img/tmp.png")} />
            <br/>
            <p className="item-title">{itemInfo.vintageTitle}</p>
        </div>
        </Link>
    )

}

export default Item