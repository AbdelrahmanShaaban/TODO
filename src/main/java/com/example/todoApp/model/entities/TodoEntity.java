package com.example.todoApp.model.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;

@Table(name = "TODO")
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TodoEntity {

    @Id
    @Column(name = "TODO_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TODO_ID_SEQ")
    @SequenceGenerator(name = "TODO_ID_SEQ", sequenceName = "TODO_ID_SEQ", allocationSize = 1)
    private Long todoId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "STATUS_ID")
    private Long statusId;

    @Column(name = "START_DATE")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @Column(name = "DEADLINE_DATE")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deadlineDate;

}
