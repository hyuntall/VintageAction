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

    console.log(userObj);
    return (
        <>
            <BrowserRouter>
                <nav>
                    <Link to="/">Home</Link>
                    <Link to="/sign-up">SignUp</Link>
                    <Link to="/sign-in">SignIn</Link>
                    <Link to="/*">return</Link>
                </nav>
                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/sign-up" element={<SignUp />} />
                    <Route path="/sign-in" element={<SignIn />} />
                    <Route path="/*" element={<App />} />
                </Routes>
            </BrowserRouter>
        </>
    );
}

export default App;