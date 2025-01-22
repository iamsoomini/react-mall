import React from 'react';
import { useSearchParams } from 'react-router-dom';

// outlet의 장점. layout에 포함되지 않았음. 
// 그럼에도 불구하고 레이아웃에 포함되어있음. index를 타기때문. 
// index의 children에 해당하는 것. 

function ListPage() {

    // url 뒤에 붙는 쿼리스트링을 가져옴.
    const [queryParams]= useSearchParams();

    // /list?page=1&size=10
    const page = queryParams.get('page')? parseInt(queryParams.get('page')): 1;
    const size = queryParams.get('size')? parseInt(queryParams.get('size')): 10;
    

return (
    <div className="p-4 w-full bg-white ">
    <div className="text-3xl font-extrabold">
    Todo List Page Component --- {page} / {size} 
    </div>
    </div>
    );
}
export default ListPage;