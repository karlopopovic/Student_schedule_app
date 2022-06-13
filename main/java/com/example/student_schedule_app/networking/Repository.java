package com.example.student_schedule_app.networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {

    private static Retrofit retrofit;
    public static final String BASE_URL = "http://10.0.2.2:8080/";


    public static Retrofit getRetrofitInstance(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder().
                            baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

        }

        return retrofit;
    }
}
