import React, {useEffect, useState} from 'react';
import axios from 'axios';
import AppRouter from './AppRouter';
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import Home from '../routes/Home';
import SignUp from '../routes/SignUp';
import SignIn from '../routes/SignIn';

function App() {
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [userObj ,setUserObj] = useState(null);
    console.log(userObj)
    return (
        <>
            Header 들어갈 곳
            <AppRouter isLoggedin={isLoggedIn} userObj={userObj}/>
            Footer 들어갈 곳
        </>
    );
}

export default App;