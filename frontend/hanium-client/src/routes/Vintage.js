import React, { useEffect, useState } from "react";
import axios from "axios";
import Item from "../components/Item";
import "../css/Vintage.css"
const Vintage = () => {
    const [itemList, setItemList] = useState(null)
    // 렌더링 시 중고 상품 리스트 정보 요청
    const getItemList = () => {
        axios.get('/api/vintages/')
        .then(response => {
        setItemList(response.data)
        })
    }
    useEffect(getItemList, [])
    return (
        <>
        <h1>중고 상품 리스트</h1>
            <div className="vintage-container">
                {/*setItemList 배열의 갯수만큼 Item 컴포넌트 생성 및 해당 컴포넌트에 아이템 정보 전달 */}
                {
                    itemList && itemList.slice(0).reverse().map(item => (
                        <Item key={item.vintageId} itemInfo={item}/>
                    ))
                }
            </div>
        </>
    )
}

export default Vintage;