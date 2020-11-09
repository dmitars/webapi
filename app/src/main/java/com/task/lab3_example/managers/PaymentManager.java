package com.task.lab3_example.managers;


import com.task.lab3_example.api.PaymentApi;
import com.task.lab3_example.data.Payment;

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

    public String sentPayment(Payment payment) {

        paymentApi.sentPayment(payment).enqueue(new Callback<String>() {
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