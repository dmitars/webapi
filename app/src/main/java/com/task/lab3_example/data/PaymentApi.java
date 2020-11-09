package com.task.lab3_example.data;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PaymentApi {
    @POST
    Call<String>sentPayment(@Query("startDate") String startDate,
                            @Query("endDate") String endDate,
                            @Query("function") String functionName);
}
