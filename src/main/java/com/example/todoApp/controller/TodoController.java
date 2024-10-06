package com.example.todoApp.controller;

import com.example.todoApp.exceptionHandler.TodoValidation;
import com.example.todoApp.model.APIResponse;
import com.example.todoApp.model.entities.TodoEntity;
import com.example.todoApp.service.TodoAuditService;
import com.example.todoApp.service.TodoService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;

@RestController
@Controller
@RequestMapping("/api")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @Autowired
    APIResponse apiResponse;
    @Autowired
    private TodoAuditService todoAuditService;


    @GetMapping("/getAllTodo")
    public ResponseEntity<APIResponse> getAllTodo() throws NotFoundException {

        if (todoService.getAllTodo().isEmpty() || todoService.getAllTodo() == null) {
            throw new NotFoundException("No Data Found") ;
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
    public ResponseEntity<APIResponse> getTodoByID(@RequestParam Long id) throws ValidationException, NotFoundException {

        TodoValidation.validateTodoId(id);

        if (todoService.findTODOById(id).isEmpty()) {
            throw new NotFoundException("Please Enter Id Correctly") ;
        }
        apiResponse.setStatus(HttpStatus.OK);
        apiResponse.setStatusCode(HttpStatus.OK.value());
        apiResponse.setBody(todoService.findTODOById(id));
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping("/insertNewTodo")
    public ResponseEntity<APIResponse> insertNewTodo(@RequestBody TodoEntity todoEntity) throws ValidationException {

        TodoValidation.validateTodoEntity(todoEntity);

        todoService.insertNewTODO(todoEntity);
        apiResponse.setStatus(HttpStatus.OK);
        apiResponse.setStatusCode(HttpStatus.OK.value());
        apiResponse.setBody("Insert Successfully");
        todoAuditService.insertTodoAudit("Abdelrahman", "insertNewTodo");
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }

    @PostMapping("/updateTodo")
    public ResponseEntity<APIResponse> updateTodo(@RequestBody TodoEntity todoEntity) throws ValidationException, NotFoundException {
        TodoValidation.validateTodoEntity(todoEntity);

        if (todoService.findTODOById(todoEntity.getTodoId()).isEmpty()) {
            throw new NotFoundException("Please Enter Valid Id");
        }
        todoService.UpdateTODO(todoEntity);
        apiResponse.setStatus(HttpStatus.OK);
        apiResponse.setStatusCode(HttpStatus.OK.value());
        apiResponse.setBody("Update Successfully");
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("/deleteTodoByID")
    public ResponseEntity<APIResponse> deleteTodoByID(@RequestParam Long id) throws ValidationException, NotFoundException {

        TodoValidation.validateTodoId(id);

        if (todoService.findTODOById(id).isEmpty()) {
            throw new NotFoundException("Please Enter a valid todo ID") ;
        }

        todoService.deleteTodoByID(id);
        apiResponse.setStatus(HttpStatus.OK);
        apiResponse.setStatusCode(HttpStatus.OK.value());
        apiResponse.setBody("Todo successfully deleted");
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/getTodoBySearch")
    public ResponseEntity<APIResponse> getTodoBySearch(@RequestBody TodoEntity todoEntity) {

        apiResponse.setStatus(HttpStatus.OK);
        apiResponse.setStatusCode(HttpStatus.OK.value());
        apiResponse.setBody(todoService.getTodoBySearch(todoEntity));
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/searchSpecification")
    public ResponseEntity<APIResponse> searchSpecification(@RequestParam(defaultValue = "") String columnName,
                                                           @RequestParam(defaultValue = "") String value) throws ValidationException, NotFoundException {

        TodoValidation.validateColumnName(columnName);
        if (columnName.isEmpty() && value.isEmpty()) {
            apiResponse.setStatus(HttpStatus.OK);
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setBody(todoService.getAllTodo());
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        }

        if (value.isEmpty()) {
            TodoValidation.createAPIResponse("Please Enter Value");
        }

        if (todoService.searchSpecification(columnName, value).isEmpty()) {
            throw new NotFoundException("No Data Found");
        }

        apiResponse.setStatus(HttpStatus.OK);
        apiResponse.setStatusCode(HttpStatus.OK.value());
        apiResponse.setBody(todoService.searchSpecification(columnName, value));
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping("/insertNewTodoUsingSave")
    public ResponseEntity<APIResponse> insertNewTodoUsingSave(@RequestBody TodoEntity todoEntity) throws ValidationException {

        TodoValidation.validateTodoEntity(todoEntity);
        todoAuditService.insertTodoAudit("Abdelrahman", "insertNewTodo");
        apiResponse.setStatus(HttpStatus.OK);
        apiResponse.setStatusCode(HttpStatus.OK.value());
        apiResponse.setBody(todoService.insertNewTODOUseSave(todoEntity));
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }

    @PutMapping("/updateTodoUsingSave")
    public ResponseEntity<APIResponse> updateTodoUsingSave(@RequestBody TodoEntity todoEntity) throws ValidationException, NotFoundException {
        TodoValidation.validateTodoEntity(todoEntity);

        if (todoService.findTODOById(todoEntity.getTodoId()).isEmpty()) {
            throw new NotFoundException("Please Enter Valid Id");
        }
        TodoEntity updatedTodo = todoService.UpdateTODOUseSave(todoEntity);
        apiResponse.setStatus(HttpStatus.OK);
        apiResponse.setStatusCode(HttpStatus.OK.value());
        apiResponse.setBody(updatedTodo);
        return ResponseEntity.ok(apiResponse);

    }
}