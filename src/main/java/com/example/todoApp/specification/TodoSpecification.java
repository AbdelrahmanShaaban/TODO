package com.example.todoApp.specification;

import com.example.todoApp.model.entities.TodoEntity;
import org.springframework.data.jpa.domain.Specification;

public class TodoSpecification {

    public static Specification<TodoEntity> search(String columnName, String value) {
        return (root, query, criteriaBuilder) -> {
            return switch (columnName) {
                case "todoId" -> criteriaBuilder.equal(root.get("todoId"), value);
                case "title" -> criteriaBuilder.like(root.get("title"), "%" + value + "%");
                case "description" -> criteriaBuilder.like(root.get("description"), "%" + value + "%");
                case "statusId" -> criteriaBuilder.equal(root.join("todoStatus").get("statusId"), Long.valueOf(value));
                case "status" -> criteriaBuilder.like(root.join("todoStatus").get("status"), "%" + value + "%");
                default -> throw new IllegalArgumentException("Invalid column: " + columnName);
            };
        };

    }

}
