package org.jrock.mallapi.repository;


import lombok.extern.log4j.Log4j2;
import org.jrock.mallapi.entity.Todo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
@Log4j2
public class TodoRepositoryTests {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void test1(){

        Assertions.assertNotNull(todoRepository);

        log.info(todoRepository.getClass().getName());

    }

    @Test
    public void testInsert(){

        final Todo todo = Todo.builder()
                .title("Title")
                .content("Content...")
                .dueDate(LocalDate.of(2024,01,21))
                .build();

        Todo result = todoRepository.save(todo);

        log.info(result);
    }


    @Test
    public void testRead(){
//        read는 pk 값 설정해주면 읽어오는것

        Long tno = 1L;   // pk에 해당 하는 값을 설정

        Optional<Todo> result = todoRepository.findById(tno);

        Todo todo = result.orElseThrow();
        // 문제 있으면 던지고 아니면 받아와

        log.info(todo);

    }


    @Test
    public void testUpdate(){

        //먼저 로딩 하고 엔티티 객체를 변경 /setter
        Long tno = 1L;   // pk에 해당 하는 값을 설정

        Optional<Todo> result = todoRepository.findById(tno);

        Todo todo = result.orElseThrow();    // select와 동일함

        todo.changeTitle("Update Title");
        todo.changeContent("Update content");
        todo.changeComplete(true);

        todoRepository.save(todo);
    }

    @Test
    public void testPaging(){

//    페이지 번호는 0부터
        Pageable pageable = (Pageable) PageRequest.of(0,10, Sort.by("tno").descending());

        Page<Todo> result = todoRepository.findAll((org.springframework.data.domain.Pageable) pageable);

        log.info(result.getTotalElements());

        log.info(result.getContent());

    }

//    @Test
//    public void testSearch1(){
//
//        todoRepository.search1();
//
//    }
}
