package org.jrock.mallapi.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jrock.mallapi.dto.PageRequestDto;
import org.jrock.mallapi.dto.PageResponseDto;
import org.jrock.mallapi.dto.TodoDto;
import org.jrock.mallapi.service.TodoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class todoController {

    private final TodoService todoService;

    @GetMapping("/{tno}")
    public TodoDto get(@PathVariable("tno") Long tno){

        return todoService.get(tno);
    }

    @GetMapping("/list")
    public PageResponseDto<TodoDto> list(PageRequestDto pageRequestDto){

        log.info("list....." + pageRequestDto);

        return todoService.getList(pageRequestDto);
    }
}
