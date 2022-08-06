import React, {useEffect, useState} from 'react';

import AppRouter from './AppRouter';
import "../css/App.css"
function App() {
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [memberObj ,setMemberObj] = useState(null);

    
    const getLoggedInfo = () => {
        if(localStorage.getItem("memberObj") != null){
            console.log(JSON.parse(localStorage.getItem("memberObj")).memberName)
            const LoggedInObj = JSON.parse(localStorage.getItem("memberObj"))

            setMemberObj({
                memberId: LoggedInObj.memberId,
                memberName: LoggedInObj.memberName,
                memberPassword: LoggedInObj.memberPassword
            })
        }
    }
    useEffect(getLoggedInfo, []);
    const refreshMember = (memberObj) => {
        // 유저 정보 변경 시 리프레시 하는 함수
        setIsLoggedIn(true);
        if (memberObj){
            setMemberObj({
                memberId: memberObj.memberId,
                memberName: memberObj.memberName,
                memberPassword: memberObj.memberPassword
              });
            localStorage.setItem('memberObj', JSON.stringify(memberObj))
        }else{
            setMemberObj(null)
            localStorage.removeItem('memberObj')
        }
    }
    return (
        <div className='app-container'>
            <AppRouter isLoggedin={isLoggedIn} memberObj={memberObj} refreshMember={refreshMember}/>
            Footer 들어갈 곳
        </div>
    );
}

export default App;