package com.grp7.projectC.customresponses;

import java.time.LocalDateTime;

public class APIResponse<T> {
    private LocalDateTime timestamp;

    private int status;

    private String message;

    private String path;

    private T details;

    public APIResponse() {}

    public APIResponse(LocalDateTime timestamp, int status, T details, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.details = details;
        this.message = message;
        this.path = path;
    }

    public LocalDateTime getTimestamp() { return timestamp; }

    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public int getStatus() { return status; }

    public void setStatus(int status) { this.status = status; }

    public T getDetails() { return details; }

    public void setDetails(T details) { this.details = details; }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    public String getPath() { return path; }

    public void setPath(String path) { this.path = path; }

}
