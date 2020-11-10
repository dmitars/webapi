package com.task.lab3_example.api;

import com.task.lab3_example.data.Book;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface BookApi {
    @GET("books")
    Call<List<Book>> getBooks();

    @POST("books/addBook")
    Call<Book> addBook(@Body Book data, @Query("token") String token);

    @PUT("books/updateBook")
    Call<Book> updateBook(@Body Book data, @Query("token") String token);

    @DELETE("books/removeBook")
    Call<Book> removeBook(@Query("id") long id, @Query("token") String token);

    @POST("books/orderBook")
    Call<Void> orderBook(@Body Book data, @Query("token") String token);
}
