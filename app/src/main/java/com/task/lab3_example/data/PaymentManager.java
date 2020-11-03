package com.task.lab3_example.data;


import android.content.Context;
import android.content.SharedPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentManager extends WebManager {
    PaymentApi paymentApi;
    String answer;
    private static PaymentManager instance;
    //private Context mainContext;

    private PaymentManager() {
        super();
        paymentApi = retrofit.create(PaymentApi.class);
    }

    public static PaymentManager getInstance() {
        if (instance == null)
            instance = new PaymentManager();
        return instance;
    }

    public String sentPayment(String startDate, String endDate, String functionName) {

        paymentApi.sentPayment(startDate, endDate, functionName).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //Strings.addAll(response.body());
                answer = response.body();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                answer = "connection failed";
            }
        });

        return answer;
    }
}
