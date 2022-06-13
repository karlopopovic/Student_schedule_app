package com.example.student_schedule_app.model;

public class Degree {

    private String id;
    private String name;

    public Degree( String name) {

        this.name = name;
    }

    public Degree() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
