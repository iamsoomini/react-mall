package org.jrock.mallapi.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jrock.mallapi.dto.PageRequestDto;
import org.jrock.mallapi.dto.PageResponseDto;
import org.jrock.mallapi.dto.TodoDto;
import org.jrock.mallapi.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @PostMapping("/")
    public Map<String,Long> register(@RequestBody TodoDto dto){

        log.info("todoDto: {}", dto);

        Long tno = todoService.register(dto);

        return Map.of("TNO", tno);
    }

    @PostMapping("/{tno}")
    public Map<String, String> modify(@PathVariable("tno") Long tno,
                                        @RequestBody TodoDto dto){

        dto.setTno(tno);

        todoService.modify(dto);

        return Map.of("RESULT","SUCCESS");
    }

    @DeleteMapping("/{tno}")
    public Map<String, String> remove(@PathVariable("tno") Long tno){

        todoService.remove(tno);

        return Map.of("RESULT","SUCCESS");
    }


}
