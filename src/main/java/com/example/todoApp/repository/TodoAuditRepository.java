package com.example.todoApp.repository;

import com.example.todoApp.model.entities.TodoAudit;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface TodoAuditRepository extends JpaRepository<TodoAudit ,Long> {
    @Modifying
    @Transactional
    @Query(value="INSERT INTO TODO_AUDIT (USER_NAME,ACTION_NAME) VALUES (?,?)" ,  nativeQuery = true)

    void insertTodoAudit (String username , String actionName) ;
}
