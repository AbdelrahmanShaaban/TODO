package com.example.todoApp.controller;

import com.example.todoApp.exceptionHandler.TodoValidation;
import com.example.todoApp.model.APIResponse;
import com.example.todoApp.model.entities.TodoEntity;
import com.example.todoApp.service.TodoAuditService;
import com.example.todoApp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Controller
@RequestMapping("/api")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @Autowired
    private TodoValidation todoValidation;
    @Autowired
    APIResponse apiResponse;
    @Autowired
    private TodoAuditService todoAuditService;


    @GetMapping("/getAllTodo")
    public ResponseEntity<APIResponse> getAllTodo() {

        if (todoService.getAllTodo().isEmpty() || todoService.getAllTodo() == null) {
            apiResponse.setStatus(HttpStatus.NOT_FOUND);
            apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
        apiResponse.setStatus(HttpStatus.OK);
        apiResponse.setStatusCode(HttpStatus.OK.value());
        apiResponse.setBody(todoService.getAllTodo());
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/getAllTodoBySort")
    public ResponseEntity<APIResponse> getAllTodoBySort(@RequestParam(defaultValue = "todoId") String sortBy,
                                                        @RequestParam(defaultValue = "ASC") String direction) {

        apiResponse.setStatus(HttpStatus.OK);
        apiResponse.setStatusCode(HttpStatus.OK.value());
        apiResponse.setBody(todoService.getAllTodoBySort(sortBy, direction));
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/getTodoByID")
    public ResponseEntity<APIResponse> getTodoByID(@RequestParam Long id) {

        APIResponse validationResponse = todoValidation.validateTodoId(Optional.ofNullable(id));
        if (validationResponse != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationResponse);
        }

        if (todoService.findTODOById(id).isEmpty()) {
            apiResponse.setStatus(HttpStatus.NOT_FOUND);
            apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
            apiResponse.setBody("Please Enter Id Correctly or insert this id");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
        apiResponse.setStatus(HttpStatus.OK);
        apiResponse.setStatusCode(HttpStatus.OK.value());
        apiResponse.setBody(todoService.findTODOById(id));
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/insertNewTodo")
    public ResponseEntity<APIResponse> insertNewTodo(@RequestBody TodoEntity todoEntity) {

        APIResponse validationResponse = todoValidation.validateTodoEntity(todoEntity);

        if (validationResponse != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationResponse);
        }
        todoService.insertNewTODO(todoEntity);
        todoAuditService.insertTodoAudit("Abdelrahman" , "insertNewTodo");
        apiResponse.setStatus(HttpStatus.OK);
        apiResponse.setStatusCode(HttpStatus.OK.value());
        apiResponse.setBody("Insert Successful");
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }

    @PostMapping("/updateTodo")
    public ResponseEntity<APIResponse> updateTodo(@RequestBody TodoEntity todoEntity) {
        APIResponse validationResponse = todoValidation.validateTodoEntity(todoEntity);

        if (validationResponse != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationResponse);
        }

        if (todoService.findTODOById(todoEntity.getTodoId()).isEmpty()) {
            apiResponse.setStatus(HttpStatus.NOT_FOUND);
            apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
            apiResponse.setBody("Please Enter a valid todo ID or insert new todo ID");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }

        todoService.UpdateTODO(todoEntity);
        apiResponse.setStatus(HttpStatus.OK);
        apiResponse.setStatusCode(HttpStatus.OK.value());
        apiResponse.setBody("Todo successfully updated");
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/deleteTodoByID")
    public ResponseEntity<APIResponse> deleteTodoByID(@RequestParam Long id) {

        APIResponse validationResponse = todoValidation.validateTodoId(Optional.ofNullable(id));

        if (validationResponse != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationResponse);
        }

        if (todoService.findTODOById(id).isEmpty()) {
            apiResponse.setStatus(HttpStatus.NOT_FOUND);
            apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
            apiResponse.setBody("Please Enter a valid todo ID");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }

        todoService.deleteTodoByID(id);
        apiResponse.setStatus(HttpStatus.OK);
        apiResponse.setStatusCode(HttpStatus.OK.value());
        apiResponse.setBody("Todo successfully deleted");
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/getTodoBySearch")
    public ResponseEntity<APIResponse> getTodoBySearch(@RequestBody TodoEntity todoEntity) {

        todoService.getTodoBySearch(todoEntity);
        apiResponse.setStatus(HttpStatus.OK);
        apiResponse.setStatusCode(HttpStatus.OK.value());
        apiResponse.setBody(todoService.getTodoBySearch(todoEntity));
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/searchSpecification")
    public ResponseEntity<APIResponse> searchSpecification(@RequestParam(defaultValue = "") String columnName,
                                                           @RequestParam(defaultValue = "") String value) {


        APIResponse validationResponse = todoValidation.validateColumnName(columnName);
        if (columnName.isEmpty() && value.isEmpty()) {
            apiResponse.setStatus(HttpStatus.OK);
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setBody(todoService.getAllTodo());
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }

        if (validationResponse != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationResponse);
        }

        if (value.isEmpty()) {
            apiResponse.setStatus(HttpStatus.BAD_REQUEST);
            apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
            apiResponse.setBody("Please Enter a value");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }

        if (todoService.searchSpecification(columnName, value).isEmpty()) {
            apiResponse.setStatus(HttpStatus.NOT_FOUND);
            apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
            apiResponse.setBody("No Data Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
        apiResponse.setStatus(HttpStatus.OK);
        apiResponse.setStatusCode(HttpStatus.OK.value());
        apiResponse.setBody(todoService.searchSpecification(columnName, value));
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
