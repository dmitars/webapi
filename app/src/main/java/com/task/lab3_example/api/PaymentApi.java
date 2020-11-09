package com.task.lab3_example.api;

import com.task.lab3_example.data.Payment;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PaymentApi {
    @POST
    Call<String>sentPayment(@Query("payment") Payment payment);
}