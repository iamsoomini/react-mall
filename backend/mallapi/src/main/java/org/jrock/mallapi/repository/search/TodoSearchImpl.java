package org.jrock.mallapi.repository.search;

import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.jrock.mallapi.dto.PageRequestDto;
import org.jrock.mallapi.entity.QTodo;
import org.jrock.mallapi.entity.Todo;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

@Log4j2
public class TodoSearchImpl extends QuerydslRepositorySupport implements TodoSearch {

    public TodoSearchImpl(){
        super(Todo.class);
    }


    // querydsl 세팅과 사용방식 > testSearch1도 만들어서 테스트해봄
    @Override
    public Page<Todo> search1(PageRequestDto pageRequestDto){

        log.info("search1..................");

        // 쿼리를 위한 기본 객체
        QTodo todo = QTodo.todo;

        // 상속 받았기때문에 from을 써서 to'do의 쿼리를 가져오는것.
        JPQLQuery<Todo> query = from(todo);

//        // 해당 쿼리의 where조건을 설정: title에 "1"이 포함
//        query.where(todo.title.contains("1"));

        // 페이지네이션을 만듬
        Pageable pageable = PageRequest.of(
                pageRequestDto.getPage() - 1,
                pageRequestDto.getSize(),
                Sort.by("tno").descending());

        // 페이지네이션 적용
        this.getQuerydsl().applyPagination(pageable, query);

        // 쿼리를 실행 - 목록 데이터 가져올때 사용
        List<Todo> list = query.fetch();

        // 쿼리를 실행 - 카운트
        // 유의: long 타입으로 반환함
        long total= query.fetchCount();

        return new PageImpl<>(list, pageable, total);
    }
}
