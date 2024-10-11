package com.example.todoApp.model;

import com.example.todoApp.model.entities.TodoStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoResponse {

    private Long todoId;

    private String title;

    private String description;

    private TodoStatus todoStatus;

    private Date startDate;

    private Date deadlineDate;
}
