import React from "react";
import { useSearchParams } from "react-router-dom";
import ListComponent from "../../components/todo/ListComponent";
import PageComponent from "../../components/common/PageComponent";
// outlet의 장점. layout에 포함되지 않았음.
// 그럼에도 불구하고 레이아웃에 포함되어있음. index를 타기때문.
// index의 children에 해당하는 것.

function ListPage() {
  return (
    <div className="p-4 w-full bg-white ">
      <div className="text-3xl font-extrabold">
        Todo List Page Component ---
      </div>

      <ListComponent />
    </div>
  );
}
export default ListPage;
