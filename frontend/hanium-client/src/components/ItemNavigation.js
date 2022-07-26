import React from "react";
import {Link} from "react-router-dom"

const Navigation = () => (
    <nav>
        <ul className="item-navigator">
            <li>
                <Link to="/vintage" className="vintage">
                    중고
                </Link>
            </li>
            <li>
                <Link to="/aa" className="뭐였지">
                    경매
                </Link>
            </li>
        </ul>
    </nav>
)

export default Navigation