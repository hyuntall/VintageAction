import React from "react";
import {Link} from "react-router-dom"
import "../css/Navigation.css"
const ItemNavigation = () => (
    <div className="item-navigater-container">
        <ul className="item-navigator">
            <li>
                <Link to="/vintage" className="vintage">
                    일반 거래
                </Link>
            </li>
            <img className="icon" src={require("../img/logo.png")} />
            <li>
                <Link to="/aa" className="뭐였지">
                    경매 거래
                </Link>
            </li>
        </ul>
    </div>
)

export default ItemNavigation