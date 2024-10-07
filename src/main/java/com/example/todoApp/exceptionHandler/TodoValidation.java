package com.example.todoApp.exceptionHandler;

import com.example.todoApp.model.APIResponse;
import com.example.todoApp.model.entities.TodoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.xml.bind.ValidationException;
import java.util.Optional;
import java.util.Set;

@Component
public class TodoValidation {

    @Autowired
    APIResponse apiResponse;
    private static final Set<String> VALID_TODO_COLUMNS = Set.of("todoId", "title", "description", "statusId", "status", "startDate", "deadlineDate");

    public static void validateColumnName(String columnName) throws ValidationException {

        if (!VALID_TODO_COLUMNS.contains(columnName)) {
            createAPIResponse("Invalid column name: " + columnName);
        }

    }

    public static void validateTodoId(Long todoId) throws ValidationException {

        if (todoId == null) {
            createAPIResponse("Please Enter a valid todo ID");
        }

    }

    public static void validateTodoEntity(TodoEntity todoEntity) throws ValidationException {

        if (todoEntity.getStartDate() == null || todoEntity.getDeadlineDate() == null) {
            createAPIResponse("Please Enter Start date or deadline date");
        }

        if (todoEntity.getStartDate().after(todoEntity.getDeadlineDate())) {
            createAPIResponse("Please Enter Start date before deadline date");
        }

        if (todoEntity.getTitle() == null || todoEntity.getTitle().isEmpty()) {
            createAPIResponse("Please Enter title");

        }

        if (todoEntity.getDescription() == null || todoEntity.getDescription().isEmpty()) {
            createAPIResponse("Please Enter description");

        }

        if (todoEntity.getTodoStatus() == null) {
            createAPIResponse("Please Enter Status Id");
        }
    }

    public static void createAPIResponse(String massage) throws ValidationException {
        throw new ValidationException(massage);

    }

}
