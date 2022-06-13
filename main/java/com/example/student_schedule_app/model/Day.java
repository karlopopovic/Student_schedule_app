package com.example.student_schedule_app.model;

import com.example.student_schedule_app.enums.DayType;
import com.google.gson.annotations.SerializedName;

public class Day {

    @SerializedName("description")
    private String description;
    @SerializedName("a_type")
    private String subDescription;
    @SerializedName("begin_time")
    private String begin_time;
    @SerializedName("end_time")
    private String end_time;


    public Day(String description, String subDescription, String begin_time, String end_time) {
        this.description = description;
        this.subDescription = subDescription;
        this.begin_time = begin_time;
        this.end_time = end_time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubDescription() {
        return subDescription;
    }

    public void setSubDescription(String subDescription) {
        this.subDescription = subDescription;
    }

    public String getBegin_time() {
        return begin_time;
    }

    public void setBegin_time(String begin_time) {
        this.begin_time = begin_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
}

