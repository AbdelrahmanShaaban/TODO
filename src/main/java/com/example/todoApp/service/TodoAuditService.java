package com.example.todoApp.service;

import com.example.todoApp.repository.TodoAuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoAuditService {

    @Autowired
    private TodoAuditRepository todoAuditRepository;

    public void insertTodoAudit(String username, String actionName) {
        todoAuditRepository.insertTodoAudit(username, actionName);
    }

}
