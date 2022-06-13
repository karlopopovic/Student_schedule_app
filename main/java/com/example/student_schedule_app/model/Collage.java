package com.example.student_schedule_app.model;

import com.google.firebase.firestore.DocumentId;

public class Collage {
    @DocumentId
    private String id;
    private String name;

    public Collage( String name) {

        this.name = name;
    }

    public Collage( ) {

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
        return name;
    }
}
