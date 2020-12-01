package com.task.lab3_example.api;

import com.task.lab3_example.data.Payment;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PaymentApi {
    @POST("payment")
    Call<String>sentPayment(@Body Payment payment);
}
