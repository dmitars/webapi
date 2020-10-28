package com.task.lab3_example.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface BookApi {
    @GET("books")
    Call<List<Book>> getBooks();

    @POST("addBook")
    Call<Book> addBook(@Body Book data);

    @POST("updateBook")
    Call<Book> updateBook(@Body Book data);

    @POST("removeBook")
    Call<Book> removeBook(@Body Book data);

    @POST("orderBook")
    Call<Book> orderBook(@Body Book data);
}
