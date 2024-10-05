package com.example.todoApp.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "TODO_STATUS")
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TodoStatus {

    @Id
    @Column(name = "STATUS_ID")
    private Long statusId ;
    @Column(name = "STATUS")
    private String status ;
}
