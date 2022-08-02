import { React, useState} from "react";
import axios from 'axios';
import { useNavigate } from "react-router-dom";

const SignInForm =() => {
    const navigate = useNavigate();
    const [id, setId] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");
    //const [memberObj, setMemberObj] = useState(null);
    const onChange = (event) => {
        const {target: {name, value}} = event;
        if(name === "id"){
            setId(value)
        }else if(name === "password"){
            setPassword(value)
        }
    }
    const onSubmit = async (event) => {
        // 회원가입 or 로그인 버튼 ( newAccount에 따라 )
        event.preventDefault();
        // 입력받은 데이터를 객체에 담아
        // 회원가입 api에 post 요청
        axios.post('/api/members/login', {
            id: id,
            password: password
        })
        .then(response => {
            console.log(response.data)
            navigate("/", {
                state:{
                    isLoggedIn:true, 
                    userObj:{
                        id: id,
                        name: id,
                        password: password
                    }
                }
            })})
        .catch(error => console.log(error.response.data))
    };

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
                name="password"
                type="password" 
                placeholder="Password" 
                required 
                value={password}
                onChange={onChange}
                className="authInput"/>

                <input type="submit" 
                className="authInput authSubmit"
                value="Log In"/>
                {error && <span className="authError">{error}</span>}
            </form>
        </>
    )
}
export default SignInForm;