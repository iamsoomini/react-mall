package org.jrock.mallapi.service;

import jakarta.transaction.Transactional;
import org.jrock.mallapi.dto.PageRequestDto;
import org.jrock.mallapi.dto.PageResponseDto;
import org.jrock.mallapi.dto.TodoDto;
import org.jrock.mallapi.entity.Todo;


@Transactional
public interface TodoService {

    TodoDto get(Long tno);

    // 등록된 객체를 받거나, pk 받거나 두가지 방식의 리턴방식 존재함.
    Long register(TodoDto dto);

    // 수정 및 삭제는 실패하면 큰일 나는것. 그래서 void로 받고 문제가 있으면 예외처리하는게 맞음.
    void modify(TodoDto dto);

    void remove(Long tno);

    PageResponseDto<TodoDto> getList(PageRequestDto pageRequestDto);

    // modelMapper 기능과 동일한거임. 쿼리가 간단할땐 이게 쉬울 수 있음.
    default TodoDto entityToDto(Todo todo){

        return
                TodoDto.builder()
                        .tno(todo.getTno())
                        .title(todo.getTitle())
                        .content(todo.getContent())
                        .complete(todo.isComplete())
                        .dueDate(todo.getDueDate())
                        .build();
    }


    default Todo dtoToEntity(TodoDto todoDto){

        return
                Todo.builder()
                        .tno(todoDto.getTno())
                        .title(todoDto.getTitle())
                        .content(todoDto.getContent())
                        .complete(todoDto.isComplete())
                        .dueDate(todoDto.getDueDate())
                        .build();
    }
}
