package com.task.lab3_example.data;

import retrofit2.Call;
import retrofit2.http.POST;

public interface PaymentApi {
    @POST
    Call<String>sentPayment(String startDate, String endDate, String functionName);
}
