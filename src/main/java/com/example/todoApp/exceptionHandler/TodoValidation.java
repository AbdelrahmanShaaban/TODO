package com.example.todoApp.exceptionHandler;

import com.example.todoApp.model.APIResponse;
import com.example.todoApp.model.entities.TodoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
public class TodoValidation {

    @Autowired
    APIResponse apiResponse ;

    private static final Set<String> VALID_TODO_COLUMNS = Set.of("todoId", "title", "description", "statusId", "startDate", "deadlineDate");

    public APIResponse validateColumnName(String columnName) {

        if (!VALID_TODO_COLUMNS.contains(columnName)) {
            apiResponse.setStatus(HttpStatus.BAD_REQUEST);
            apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
            apiResponse.setBody("Invalid column name: " + columnName);
            return apiResponse;
        }

        return null;
    }
    public APIResponse validateTodoId(Optional<Long> todoId) {

        if (todoId.isEmpty()) {
            apiResponse.setStatus(HttpStatus.BAD_REQUEST);
            apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
            apiResponse.setBody("Please Enter a valid todo ID");
            return apiResponse;
        }

        return null;
    }

    public APIResponse validateTodoEntity(TodoEntity todoEntity) {

        if (todoEntity.getStartDate() == null || todoEntity.getDeadlineDate() == null) {
            apiResponse.setStatus(HttpStatus.BAD_REQUEST);
            apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
            apiResponse.setBody("Please Enter Start date or deadline date");
            return apiResponse;
        }

        if (todoEntity.getStartDate().after(todoEntity.getDeadlineDate())) {
            apiResponse.setStatus(HttpStatus.BAD_REQUEST);
            apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
            apiResponse.setBody("Please Enter Start date before deadline date");
            return apiResponse;
        }

        if (todoEntity.getTitle() == null || todoEntity.getTitle().isEmpty()) {
            apiResponse.setStatus(HttpStatus.BAD_REQUEST);
            apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
            apiResponse.setBody("Please Enter title");
            return apiResponse;
        }

        if (todoEntity.getDescription() == null || todoEntity.getDescription().isEmpty()) {
            apiResponse.setStatus(HttpStatus.BAD_REQUEST);
            apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
            apiResponse.setBody("Please Enter description");
            return apiResponse;
        }

        if (todoEntity.getStatusId() == null) {
            apiResponse.setStatus(HttpStatus.BAD_REQUEST);
            apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
            apiResponse.setBody("Please Enter Status Id");
            return apiResponse;
        }

        return null;
    }
}
