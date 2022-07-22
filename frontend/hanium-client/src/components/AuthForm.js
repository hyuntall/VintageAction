import { React, useState} from "react";
import axios from 'axios';
//import '../auth.css'

const AuthForm =() => {
    const [id, setId] = useState("");
    const [name, setName] = useState("");
    const [password, setPassword] = useState("");
    const [newAccount, setNewAccount] = useState(true);
    const [error, setError] = useState("");
    //const [memberObj, setMemberObj] = useState(null);
    const onChange = (event) => {
        const {target: {name, value}} = event;
        if(name === "id"){
            setId(value)
        }else if(name == "name"){
            setName(value)
        }else if(name === "password"){
            setPassword(value)
        }
    }
    const onSubmit = async (event) => {
        // 회원가입 or 로그인 버튼 ( newAccount에 따라 )
        event.preventDefault();
        
        
        /* 에러 수정 필요
        try {
            axios.post('/api/members/new/', {
                id: id,
                name: name,
                password: password
            })
        .then(response => console.log(response.data))
        .catch(error => console.log(error.response.data))
        }
        catch (error) {
            console.log("error")
        }*/
    };
    // 로그인 <> 회원가입 체인지
    const toggleAccount = () => setNewAccount((prev) => !prev);
    return (
        <>
            <form onSubmit={onSubmit} className="container">
                <input 
                name="id"
                placeholder="id" 
                required 
                value={id}
                onChange={onChange}
                className="authInput"/>

                <input 
                name="name"
                placeholder="name" 
                required 
                value={name}
                onChange={onChange}
                className="authInput"/>

                <input 
                name="password"
                type="password" 
                placeholder="Password" 
                required 
                value={password}
                onChange={onChange}
                className="authInput"/>

                <input type="submit" 
                className="authInput authSubmit"
                value={newAccount ? "Create Account" : "Log In"}/>
                {error && <span className="authError">{error}</span>}
            </form>
            <span onClick={toggleAccount}
                className="authSwitch">{newAccount ? "Log In" : "Create Account"}
            </span>
        </>
    )
}
export default AuthForm;