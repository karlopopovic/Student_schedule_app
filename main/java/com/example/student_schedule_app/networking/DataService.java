package com.example.student_schedule_app.networking;

import com.example.student_schedule_app.model.Admin;
import com.example.student_schedule_app.model.Collage;
import com.example.student_schedule_app.model.Day;
import com.example.student_schedule_app.model.Degree;
import com.example.student_schedule_app.model.Room;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {

    @GET("admin")
    Call<List<Day>> getDays();

    @GET("rooms")
    Call<List<Room>> getRooms();

    @GET("collages")
    Call<List<Collage>> getCollages();

    @GET("degrees")
    Call<List<Degree>> getDegrees();

    @GET("classes")
    Call<List<Class>> getClasses();


    @GET("admins")
    Call<List<Admin>> getAdmins();


}
