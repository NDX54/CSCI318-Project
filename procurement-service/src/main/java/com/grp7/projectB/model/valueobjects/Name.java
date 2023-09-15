package com.grp7.projectB.model.valueobjects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Name {

    @Column(name = "name", unique = false, updatable = true)
    private String name;

    public Name() {}

    public Name(String name) { this.name = name; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    @Override
    public String toString() { return name; }
}
