package com.grp7.projectB.model.valueobjects;


import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Comment {

    @Column(name = "comment", unique = false, updatable = false)
    private String comment;

    public Comment() {}

    public Comment(String comment) { this.comment = comment; }

    public String getComment() { return this.comment; }

    public void setComment(String comment) { this.comment = comment; }
}