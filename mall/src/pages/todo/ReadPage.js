import React from "react";
import {
  createSearchParams,
  useNavigate,
  useParams,
  useSearchParams,
} from "react-router-dom";

function ReadPage(props) {
  const navigate = useNavigate();
  const { tno } = useParams();

  const [queryParams] = useSearchParams();

  const page = queryParams.get("page") ? parseInt(queryParams.get("page")) : 1;
  const size = queryParams.get("size") ? parseInt(queryParams.get("size")) : 10;

  // 쿼리스트링을 만들어주는 함수, 페이지 이동할때 queryStr이 따라다님
  // 수정확인
  const queryStr = createSearchParams({ page: page, size: size }).toString();

  const moveToModify = (tno) => {
    navigate({
      pathname: `/todo/modify/${tno}`,
      search: queryStr,
    });
  };

  const moveToList = () => {
    navigate({
      pathname: "/todo/list",
      search: queryStr,
    });
  };

  return (
    <div className={"text-3xl"}>
      Todo Read Page {tno}
      {/* 내가 props로 써야할것과 라우터로 써야할것을 구분하는게 좋음. */}
      <button onClick={() => moveToModify(tno)}> Test Modify </button>
      <button onClick={moveToList}> Test List </button>
    </div>
  );
}

export default ReadPage;

// 페이지 생성 -> 라우터 설정
