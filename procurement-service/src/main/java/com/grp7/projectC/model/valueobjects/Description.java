package com.grp7.projectC.model.valueobjects;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
public class Description {

    @NotBlank(message = "Description must not be blank")
    private String description;

    public Description() {}

    public Description(String description) { this.description = description; }

    public String getDescription() { return this.description; }

    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() { return description; }
}
