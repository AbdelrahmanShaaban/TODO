package com.example.todoApp.service;

import com.example.todoApp.model.entities.TodoEntity;
import com.example.todoApp.repository.TodoRepository;
import com.example.todoApp.specification.TodoSpecification;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<TodoEntity> getAllTodo() {
        return todoRepository.findAll();
    }


    public List<TodoEntity> getAllTodoBySort(String sortBy, String direction) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        return todoRepository.findAll(sort);
    }

    public Optional<TodoEntity> findTODOById(Long id) {
        return todoRepository.findById(id);
    }

    public void deleteTodoByID(Long id) {
        todoRepository.deleteById(id);
    }


    public List<TodoEntity> searchSpecification(String columnName, String value) {
        return todoRepository.findAll(TodoSpecification.search(columnName, value));
    }

    public TodoEntity insertNewTODOUseSave(TodoEntity todoEntity) {
        return todoRepository.save(todoEntity);
    }

    public TodoEntity UpdateTODOUseSave(TodoEntity todoEntity) {
        return todoRepository.save(todoEntity);

    }
    /*
    public void insertNewTODO(TodoEntity todoEntity) {
        // todoRepository.insertNewTodo(todoEntity.getTodoId(), todoEntity.getTitle(), todoEntity.getDescription(), todoEntity.getStatusId(), todoEntity.getStartDate(), todoEntity.getDeadlineDate());
    }

    public void UpdateTODO(TodoEntity todoEntity) {
//        todoRepository.updateTodo(
//                todoEntity.getTitle(),
//                todoEntity.getDescription(),
//                todoEntity.getStatusId(),
//                todoEntity.getStartDate(),
//                todoEntity.getDeadlineDate(),
//                todoEntity.getTodoId());
    }

    public List<TodoEntity> getTodoBySearch(TodoEntity todoEntity) {
//        return todoRepository.getTodoBySearch(
//                todoEntity.getTodoId(),
//                todoEntity.getTitle(),
//                todoEntity.getDescription(),
//                todoEntity.getStatusId()
//        );
        return null ;
    }
    */
}
