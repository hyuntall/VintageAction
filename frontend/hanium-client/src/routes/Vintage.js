import React, { useEffect, useState } from "react";
import axios from "axios";
import Item from "../components/Item";
import "../css/Vintage.css"
const Vintage = () => {
    const [itemList, setItemList] = useState(null)
    const getItemList = () => {
        axios.get('/api/vintages/')
        .then(response => {
        setItemList(response.data)
        console.log(response.data)
        })
    }
    useEffect(getItemList, [])
    return (
        <>
        <h1>중고 상품 리스트</h1>
            <div className="vintage-container">
                {
                    itemList && itemList.slice(0).reverse().map(item => (
                        <Item key={item.vintageId} itemName={item.vintageTitle} />
                    ))
                }
            </div>
        </>
    )
}

export default Vintage;