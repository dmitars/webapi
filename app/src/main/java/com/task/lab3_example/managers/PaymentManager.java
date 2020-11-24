package com.task.lab3_example.managers;


import com.task.lab3_example.api.PaymentApi;
import com.task.lab3_example.data.Payment;
import com.task.lab3_example.tabs.PaymentTab;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentManager extends WebManager {
    PaymentApi paymentApi;
    String answer;
    private static PaymentManager instance;
    PaymentTab paymentTab;
    //private Context mainContext;

    private PaymentManager(PaymentTab paymentTab) {
        super();
        this.paymentTab = paymentTab;
        paymentApi = retrofit.create(PaymentApi.class);
    }

    public static PaymentManager getInstance(PaymentTab paymentTab) {
        if (instance == null)
            instance = new PaymentManager(paymentTab);
        return instance;
    }

    public String sentPayment(Payment payment) {

        paymentApi.sentPayment(payment).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //Strings.addAll(response.body());
                answer = response.body();
                paymentTab.setToken(payment.getFunction(),response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                answer = "connection failed";
            }
        });

        return answer;
    }
}
