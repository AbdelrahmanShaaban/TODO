package com.example.todoApp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Component
public class APIResponse {

    protected LocalDateTime timestamp;
    protected int statusCode;
    protected HttpStatus status;
    protected Object body;
    protected String clientMessage;

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public APIResponse(int statusCode, String clientMessage, Object body) {
        this.statusCode = statusCode;
        this.clientMessage = clientMessage;
        this.body = body;
    }
}
