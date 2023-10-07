package com.grp7.projectB.model.valueobjects;

import javax.persistence.Embeddable;

@Embeddable
public class Description {

    private String description;

    public Description() {}

    public Description(String description) { this.description = description; }

    public String getDescription() { return this.description; }

    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() { return description; }
}
