package com.task.lab3_example.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class WebManager {
    Retrofit retrofit;

    protected WebManager(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://10.0.2.2:5000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
