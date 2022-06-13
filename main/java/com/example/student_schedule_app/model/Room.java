package com.example.student_schedule_app.model;

import com.google.gson.annotations.SerializedName;

public class Room {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;

    public Room( String name) {

        this.name = name;
    }

    public Room() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
