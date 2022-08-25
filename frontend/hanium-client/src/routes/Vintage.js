import React, { useEffect, useState } from "react";
import axios from "axios";
import Item from "../components/Item";
import "../css/Vintage.css";
import { Link, useLocation } from "react-router-dom";
import Pagination from "react-js-pagination";
import Top from "../components/Top";
const Vintage = () => {
  const [itemList, setItemList] = useState(null);
  const [totalPage, setTotalPage] = useState(0);
  const [page, setPage] = useState(0);
  // 렌더링 시 중고 상품 리스트 정보 요청
  const getItemList = () => {
    axios.get(`/api/vintages?page=${page}`).then((response) => {
      setItemList(response.data.vintageBoard);
      setTotalPage(response.data.totalPage);
    });
  };
  useEffect(getItemList, []);
  const handlePageChange = (page) => {
    setPage(page);
    getItemList();
  };
  return (
    <>
      <Top></Top>
      <h1>중고 상품 리스트</h1>
      <div className="vintage-container">
        {/*setItemList 배열의 갯수만큼 Item 컴포넌트 생성 및 해당 컴포넌트에 아이템 정보 전달 */}
        {itemList &&
          itemList
            .slice(0)
            .reverse()
            .map((item) => (
              <Link key={item.vintageId} to={`/vintage/${item.vintageId}`}>
                <Item itemInfo={item} />
              </Link>
            ))}
      </div>
      <Pagination
      activePage={page}
      itemsCountPerPage={10}
      totalItemsCount={450}
      pageRangeDisplayed={10}
      prevPageText={"‹"}
      nextPageText={"›"}
      onChange={handlePageChange}
      className="pagenation"
    />
    </>
  );
};

export default Vintage;
