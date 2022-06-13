package com.example.student_schedule_app.model;


import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentId;

public class Activity {
    @DocumentId
    private String activityId;
    private String collage;
    private Timestamp begin_time;
    private Timestamp end_time;
    private String room;
    private String degree;
    private String major;
    private String type;
    private String description;
    private String courseName;
    public Activity()
    {

    }

    public Activity(String collage,String major,String courseName,String room, String degree, String type, String description, Timestamp begin_time, Timestamp end_time) {
        this.collage = collage;
        this.major = major;
        this.courseName = courseName;
        this.room = room;
        this.degree = degree;
        this.type = type;
        this.description = description;
        this.begin_time = begin_time;
        this.end_time = end_time;
    }

    public String getActivityId() {
        return activityId;
    }

    public Timestamp getBegin_time() {
        return begin_time;
    }

    public Timestamp getEnd_time() {
        return end_time;
    }

    public String getRoom() {
        return room;
    }

    public String getCollage() {
        return collage;
    }

    public String getDegree() {
        return degree;
    }

    public String getMajor() {
        return major;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public void setCollage(String collage) {
        this.collage = collage;
    }

    public void setBegin_time(Timestamp begin_time) {
        this.begin_time = begin_time;
    }

    public void setEnd_time(Timestamp end_time) {
        this.end_time = end_time;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCourse(String courseName) {
        this.courseName = courseName;
    }
}
