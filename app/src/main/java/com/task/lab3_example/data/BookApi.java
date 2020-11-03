package com.task.lab3_example.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface BookApi {
    @GET("books")
    Call<List<Book>> getBooks();

    @POST("addBook")
    Call<Book> addBook(@Body Book data);

    @POST("updateBook")
    Call<Book> updateBook(@Body Book data);

    @DELETE("removeBook")
    Call<Book> removeBook(@Query("id") long id);

    @POST("orderBook")
    Call<Book> orderBook(@Body Book data);
}
