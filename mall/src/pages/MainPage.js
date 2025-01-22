import BasicLayout from "../layouts/BasicLayout";
const MainPage = () => {
  return (
// 기본적인 레이아웃을 설정해두고, 그 children에 해당하는 부분만 바꿔서 사용할 수 있음. children에 mainpage가 들어감.
    <BasicLayout>
      <div className=" text-3xl">Main Page</div>
    </BasicLayout>

  );
};
export default MainPage;
