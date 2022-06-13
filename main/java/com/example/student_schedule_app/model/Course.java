package com.example.student_schedule_app.model;

import com.google.firebase.firestore.DocumentId;

public class Course {
    @DocumentId
    private String courseId;
    private String courseName;

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public Course() {
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return courseName;
    }
}
