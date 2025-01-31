package org.jrock.mallapi.repository;

import org.jrock.mallapi.entity.Todo;
import org.jrock.mallapi.repository.search.TodoSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long>, TodoSearch {


}
