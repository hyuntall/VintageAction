import React, { useState } from "react";
import axios from "axios";

const Vintage = () => {
    const [itemList, setItemList] = useState(null)
    axios.get('/api/vintages/')
    .then(response => {
        //console.log(response.data);
        //setItemList(response.data)
        //console.log(itemList[0])
        // 한번만 렌더링 되도록 수정 필요.
    })
    return (
        <div className="vintageContainer">
            중고 상품 리스트
            {
                itemList && itemList.map(item => (
                    <p>gd</p>
                ))
            }
        </div>
    )
}

export default Vintage;