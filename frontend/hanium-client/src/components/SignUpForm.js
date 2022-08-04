import { React, useState} from "react";
import axios from 'axios';
import { useNavigate } from "react-router-dom";
//import '../auth.css'

const AuthForm =({refreshMember}) => {
    const navigate = useNavigate();
    const [id, setId] = useState("");
    const [name, setName] = useState("");
    const [password, setPassword] = useState("");
    const [newAccount, setNewAccount] = useState(true);
    const [memberObj, setMemberObj] = useState(null);
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
        // 입력받은 데이터를 객체에 담아
        // 회원가입 api에 post 요청
        setMemberObj({
            id: id,
            name: name,
            password: password
        })
        await axios.post('/api/members/new/', {
            id: id,
            name: name,
            password: password
        })
        .then(response => {
            console.log(response.data);
            setMemberObj({
                memberId: id,
                memberName: name,
                memberPassword: password
            })
            refreshMember({
                memberId: id,
                memberName: name,
                memberPassword: password
            })
            navigate("/")
        })
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
                value="Create Account"/>
                {error && <span className="authError">{error}</span>}
            </form>
        </>
    )
}
export default AuthForm;