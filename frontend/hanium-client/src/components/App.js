import React, {useEffect, useState} from 'react';
import axios from 'axios';
import AppRouter from './AppRouter';
import Header from './Header'
import "../css/App.css"
import ItemNavigation from './ItemNavigation';
function App() {
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [userObj ,setUserObj] = useState(null);
    console.log(userObj)
    return (
        <div className='app-container'>
            <Header />
            <AppRouter isLoggedin={isLoggedIn} userObj={userObj}/>
            Footer 들어갈 곳
        </div>
    );
}

export default App;