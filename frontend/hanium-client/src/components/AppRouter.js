import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import SignUp from "../routes/SignUp";
import SignIn from "../routes/SignIn";
import Home from "../routes/Home";
import Profile from "../routes/Profile";
import Header from "./Header";
import VintageUpload from "../routes/VintageUpload";
import Vintage from "../routes/Vintage";
import VintageDetail from "../routes/VintageDetail";
const AppRouter = ({ memberObj, refreshMember }) => {
    return (
        <Router>
            {/*헤더에 유저 정보 전달하여 로그인 유무에 따라 네비게이션 달리 표시 */}
            <Header memberObj={memberObj} refreshMember={refreshMember}/>
            <div className="router">
                <Routes>
                    <>
                        <Route path='/sign-up' element={<SignUp refreshMember={refreshMember}/>}/>
                        <Route path='/sign-in' element={<SignIn refreshMember={refreshMember}/>}/>
                        <Route path='/profile' element={<Profile memberObj={memberObj} refreshMember={refreshMember}/>}/>
                        <Route path='/vintage-upload' element={<VintageUpload memberObj={memberObj}/>}/>
                        <Route path='/vintage' element={<Vintage />}/>
                        <Route path="/vintage/:vintageId" element={<VintageDetail />}/>
                        <Route path='/' element={<Home/>}/>
                    </>
                </Routes>
            </div>
        </Router>
    )
}

export default AppRouter