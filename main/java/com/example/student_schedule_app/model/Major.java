package com.example.student_schedule_app.model;
import com.google.firebase.firestore.DocumentId;

public class Major {
    @DocumentId
    private String majorId;
    private String majorName;

    public Major(String majorName) {
        this.majorName = majorName;
    }

    public Major() {
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    @Override
    public String toString() {
        return majorName;
    }
}
