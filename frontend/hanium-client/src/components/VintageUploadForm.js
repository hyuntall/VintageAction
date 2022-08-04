import { React, useState} from "react";
import axios from 'axios';
import { useNavigate } from "react-router-dom";
//import '../auth.css'

const VintageUploadForm =({memberObj, refreshMember}) => {
    const [title, setTitle] = useState("");
    const [detail, setDetail] = useState("");
    const [itemName, setItemName] = useState("");
    const [price, setPrice] = useState(0);
    const [error, setError] = useState(null)
    //const [memberObj, setMemberObj] = useState(null);
    const onChange = (event) => {
        const {target: {name, value}} = event;
        if(name === "title"){
            setTitle(value)
        }else if(name == "detail"){
            setDetail(value)
        }else if(name === "item-name"){
            setItemName(value)
        }else if(name === "price"){
            setPrice(value)
        }
    }
    const onSubmit = async (event) => {
        
        event.preventDefault();
        // 입력받은 데이터를 객체에 담아
        // 회원가입 api에 post 요청
        
        await axios.post('/api/vintage/new/', {
            vintageTitle: title,
            vintageDetail: detail,
            itemName: itemName,
            itemPrice: price
        })
        .then(response => {
            console.log(response.data);
        })
        .catch(error => console.log(error.response.data))
    };
    return (
        <>
            <form onSubmit={onSubmit} className="container">
                <input 
                name="title"
                placeholder="title" 
                required 
                value={title}
                onChange={onChange}
                className="authInput"/>

                <input 
                name="detail"
                placeholder="detail" 
                required 
                value={detail}
                onChange={onChange}
                className="authInput"/>

                <input 
                name="item-name"
                placeholder="item name" 
                required 
                value={itemName}
                onChange={onChange}
                className="authInput"/>

                <input 
                name="price"
                placeholder="price" 
                required 
                value={price}
                onChange={onChange}
                className="authInput"/>

                <input type="submit" 
                className="authInput authSubmit"
                value="Upload"/>
                {error && <span className="authError">{error}</span>}
            </form>
        </>
    )
}
export default VintageUploadForm;