import { Suspense, lazy } from "react";
import { Navigate } from "react-router-dom";

const Loading = <div>Loading...</div>;

const todoRouter = () => {
  const TodoList = lazy(() => import("../pages/todo/ListPage"));
  const TodoRead = lazy(() => import("../pages/todo/ReadPage"));
  const TodoAdd = lazy(() => import("../pages/todo/AddPage"));
  const TodoModify = lazy(() => import("../pages/todo/ModifyPage"));
  
  //return이 배열형태라는 것. 
  return [
    {
      path: "list",
      element: (<Suspense fallback={Loading}><TodoList /></Suspense>),
    },
    {
      path: "",
      // todo 페이지에만 들어와도 list로 가게됨.
      element: <Navigate replace={true} to={"list"} />,
    },
    {
      path: "read/:tno",
      element: (<Suspense fallback={Loading}><TodoRead /></Suspense>),
    },
    {
        path: "add",
        element: (<Suspense fallback={Loading}><TodoAdd /></Suspense>),
    },
    {
      path: "modify/:tno",
      element: (<Suspense fallback={Loading}><TodoModify /></Suspense>),
  },

  ];
};

export default todoRouter;
