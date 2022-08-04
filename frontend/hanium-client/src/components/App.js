import React, {useEffect, useState} from 'react';
import axios from 'axios';
import AppRouter from './AppRouter';
import Header from './Header'
import "../css/App.css"
import ItemNavigation from './ItemNavigation';
function App() {
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [memberObj ,setMemberObj] = useState(null);

    const refreshMember = (memberObj) => {
        // 유저 정보 변경 시 리프레시 하는 함수
        setMemberObj({
          memberId: memberObj.memberId,
          memberName: memberObj.memberName,
          memberPassword: memberObj.memberPassword
        });
        setIsLoggedIn(true);
      }
    return (
        <div className='app-container'>
            <AppRouter isLoggedin={isLoggedIn} memberObj={memberObj} refreshMember={refreshMember}/>
            Footer 들어갈 곳
        </div>
    );
}

export default App;