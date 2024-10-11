package com.example.todoApp.service;

import com.example.todoApp.model.entities.TodoStatus;
import com.example.todoApp.repository.TodoStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoStatusService {

    @Autowired
    private TodoStatusRepository todoStatusRepository;

    public TodoStatus findTODOStatusById(Long id) {
        return todoStatusRepository.findById(id).orElse(null);
    }

}
