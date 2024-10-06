package com.example.todoApp.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(name = "TODO_AUDIT")
@NoArgsConstructor
@AllArgsConstructor
public class TodoAudit {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "todo_audit_seq")
    @SequenceGenerator(name = "todo_audit_seq", sequenceName = "TODO_AUDIT_SEQ", allocationSize = 1)
    private Long ID;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "ACTION_NAME")
    private String actionName;

    @Column(name = "ACTION_DATE")
    private Date actionDate;

    @Column(name = "UPDATED_DATE")
    private String actionUpdatedDate;
}
