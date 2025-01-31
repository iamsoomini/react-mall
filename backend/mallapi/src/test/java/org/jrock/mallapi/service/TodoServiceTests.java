package org.jrock.mallapi.service;


import lombok.extern.log4j.Log4j2;
import org.jrock.mallapi.dto.PageRequestDto;
import org.jrock.mallapi.dto.TodoDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
@Log4j2
public class TodoServiceTests {

    @Autowired
    TodoService todoService;

    @Test
    public void testGet(){

        Long tno = 1L;

        log.info(todoService.get(tno));

    }

    @Test
    public void testRegister(){

        TodoDto todoDto = TodoDto.builder()
                .title("title...")
                .content("content....")
                .dueDate(LocalDate.of(2024,12,31))
                .build();

        log.info(todoService.register(todoDto));

    }

    @Test
    public void testGetList(){

        PageRequestDto pageRequestDTO = PageRequestDto.builder().build();

        log.info(todoService.getList(pageRequestDTO));

    }
}
