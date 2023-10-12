package com.grp7.projectC.customresponses;

import java.time.LocalDateTime;
import java.util.List;

public class CustomErrorResponse {

    private List<String> details;

    private LocalDateTime timestamp;

    private int status;

    private String error;

    private String message;

    private String path;

    public CustomErrorResponse() {}

    public CustomErrorResponse(LocalDateTime timestamp, int status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    public LocalDateTime getTimestamp() { return timestamp; }

    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public int getStatus() { return status; }

    public void setStatus(int status) { this.status = status; }

    public String getError() { return error; }

    public void setError(String error) { this.error = error; }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    public String getPath() { return path; }

    public void setPath(String path) { this.path = path; }
}