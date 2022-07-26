import React from "react";
import { HashRouter as Router, Navigate, Route, Routes } from "react-router-dom";
import Auth from "../routes/Auth";
import Navigation from "./Navigation";
const AppRouter = () => {
    return (
        <Router>
            <Navigation/>
            <div className="router">
            <Routes>
                <>
                    <Route path='/sign-up' element={<Auth/>}/>
                    <Route path='/home' element={<Navigate replate to='/'/>}/>
                </>
            </Routes>
            </div>
        </Router>
    )
}

export default AppRouter