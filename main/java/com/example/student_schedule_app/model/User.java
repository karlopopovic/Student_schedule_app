package com.example.student_schedule_app.model;

import com.google.firebase.firestore.DocumentId;

import java.util.ArrayList;

public class User {
    @DocumentId
    private String userId;
    private String name;
    private String email;
    private String role;
    private ArrayList<String> courses;

    public User() {
    }

    public User(String username, String email, String password, ArrayList<String> courses, String role) {

        this.name = username;
        this.email = email;
        this.courses = courses;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<String> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<String> courses) {
        this.courses = courses;
    }
}
