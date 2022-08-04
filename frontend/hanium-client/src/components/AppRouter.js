import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import SignUp from "../routes/SignUp";
import SignIn from "../routes/SignIn";
import Home from "../routes/Home";
import Profile from "../routes/Profile";
import ItemNavigation from "./ItemNavigation";
import Navigation from "./Navigation";
import Header from "./Header";
import VintageUpload from "../routes/VintageUpload";
const AppRouter = ({ isLoggedIn, memberObj, refreshMember }) => {
    return (
        <Router>
            <Header isLoggedIn={isLoggedIn} memberObj={memberObj}/>
            <div className="router">
                <Routes>
                    <>
                        <Route path='/sign-up' element={<SignUp refreshMember={refreshMember}/>}/>
                        <Route path='/sign-in' element={<SignIn refreshMember={refreshMember}/>}/>
                        <Route path='/profile' element={<Profile memberObj={memberObj}/>}/>
                        <Route path='/vintage-upload' element={<VintageUpload memberObj={memberObj}/>}/>
                        <Route path='/' element={<Home/>}/>
                    </>
                </Routes>
            </div>
        </Router>
    )
}

export default AppRouter