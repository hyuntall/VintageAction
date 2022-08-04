import React from "react"
import '../css/Header.css'
import ItemNavigation from "./ItemNavigation"
import Navigation from "./Navigation"

const Header =({ isLoggedIn, memberObj, refreshMember }) => {

    return (
        <div className="header-container">
            <h1 className="header-title">한이음 중고경매</h1>
            <Navigation isLoggedIn={isLoggedIn} memberObj={memberObj} refreshMember={refreshMember}/>
            <ItemNavigation />
        </div>
    )

}

export default Header