import React from "react";
import { useParams } from "react-router-dom";
import ReadComponent from "../../components/todo/ReadComponent";

function ReadPage(props) {
  const { tno } = useParams();

  return (
    // <div className={"text-3xl"}>
    //   Todo Read Page {tno}
    //   {/* 내가 props로 써야할것과 라우터로 써야할것을 구분하는게 좋음. */}
    //   <button onClick={() => moveToModify(tno)}> Test Modify </button>
    //   <button onClick={moveToList}> Test List </button>
    // </div>

    <div className="font-extrabold w-full bg-white mt-6">
      <div className="text-2xl">Todo Read Page Component {tno}</div>

      <ReadComponent tno={tno} />
    </div>
  );
}

export default ReadPage;

// 페이지 생성 -> 라우터 설정
