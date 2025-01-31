package org.jrock.mallapi.repository.search;

import org.jrock.mallapi.dto.PageRequestDto;
import org.jrock.mallapi.entity.Todo;
import org.springframework.data.domain.Page;

public interface TodoSearch {

    Page<Todo> search1(PageRequestDto pageRequestDto);
}
