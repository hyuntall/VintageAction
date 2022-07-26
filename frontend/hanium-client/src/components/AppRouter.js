import React from "react";
import { HashRouter as Router, Navigate, Route, Routes } from "react-router-dom";
import SignUp from "../routes/SignUp";
import SignIn from "../routes/SignIn";
import Navigation from "./Navigation";
import Home from "../routes/Home";
const AppRouter = () => {
    return (
        <Router>
            <Navigation/>
            <div className="router">
            <Routes>
                <>
                    <Route path='/sign-up' element={<SignUp/>}/>
                    <Route path='/sign-in' element={<SignIn/>}/>
                    <Route path='/' element={<Home/>}/>
                </>
            </Routes>
            </div>
        </Router>
    )
}

export default AppRouter