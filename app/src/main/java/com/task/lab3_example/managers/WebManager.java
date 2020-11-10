package com.task.lab3_example.managers;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class WebManager {
    Retrofit retrofit;

    protected WebManager(){
/*        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);*/

        /*OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(interceptor);*/
        OkHttpClient client = UnsafeHttpClient.getUnsafeOkHttpClient();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://10.160.54.8:45455/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }
}
