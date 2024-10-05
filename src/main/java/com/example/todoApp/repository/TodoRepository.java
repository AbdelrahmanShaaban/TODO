package com.example.todoApp.repository;

import com.example.todoApp.model.entities.TodoEntity;
import com.example.todoApp.specification.TodoSpecification;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Component
@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long>, JpaSpecificationExecutor<TodoEntity> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO TODO (TODO_ID , TITLE , DESCRIPTION , STATUS_ID , START_DATE , DEADLINE_DATE) VALUES (?,?,?,?,?,?)", nativeQuery = true)
    void insertNewTodo(Long todoId, String title, String Description, Long StatusId, Date startDate, Date deadlineDate);

    @Modifying
    @Transactional
    @Query("UPDATE TodoEntity t SET t.title = :title, t.description = :description, t.statusId = :statusId, t.startDate = :startDate, t.deadlineDate = :deadlineDate WHERE t.todoId = :todoId")
    void updateTodo(
            @Param("title") String title,
            @Param("description") String description,
            @Param("statusId") Long statusId,
            @Param("startDate") Date startDate,
            @Param("deadlineDate") Date deadlineDate,
            @Param("todoId") Long todoId);

    @NonNull
    List<TodoEntity> findAll(@NonNull Sort sort);

    @Modifying
    @Transactional
    @Query("FROM TodoEntity t " +
            "WHERE (:todoId IS NULL OR t.todoId = :todoId) " +
            "AND (:title IS NULL OR t.title LIKE CONCAT('%', :title, '%')) " +
            "AND (:description IS NULL OR t.description LIKE CONCAT('%', :description, '%')) " +
            "AND (:statusId IS NULL OR t.statusId = :statusId)")
    List<TodoEntity> getTodoBySearch(
            @Param("todoId") Long todoId,
            @Param("title") String title,
            @Param("description") String description,
            @Param("statusId") Long statusId
    );
}




