package com.grp7.projectC.model.valueobjects;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
public class Comment {

    @NotBlank(message = "Comment must not be blank")
    private String comment;

    public Comment() {}

    public Comment(String comment) { this.comment = comment; }

    public String getComment() { return this.comment; }

    public void setComment(String comment) { this.comment = comment; }

    @Override
    public String toString() { return comment; }
}
