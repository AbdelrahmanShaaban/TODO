package com.example.todoApp.repository;

import com.example.todoApp.model.entities.TodoStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoStatusRepository extends JpaRepository<TodoStatus , Long> {
}
