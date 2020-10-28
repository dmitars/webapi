package com.task.lab3_example.data;

import android.provider.ContactsContract;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataManager extends WebManager{
    public static Book selectedBook;
    BookApi bookApi;
    BookCallProcessor processor;

    private static DataManager instance;

    private DataManager(){
        super();
        bookApi = retrofit.create(BookApi.class);
        processor = new BookCallProcessor();
    }

    public static DataManager getInstance(){
        if(instance == null)
            instance = new DataManager();
        return instance;
    }

    public void addBook(Book book){
        bookApi.addBook(book).enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                //books.addAll(response.body());
                System.out.println(response.body().toString());
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {

            }
        });
    }

    public List<Book>loadBooks(){
        List<Book>books = new ArrayList<>();

        bookApi.getBooks().enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                System.out.println(response.body());
                books.addAll(response.body());
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                System.out.println("something");
            }
        });
        return books.size() == 0?null:books;
    }

    public String updateBook(Book book){
        bookApi.updateBook(book).enqueue(processor);
        return processor.getAnswer();
    }

    public String removeBook(){
        bookApi.removeBook(selectedBook).enqueue(processor);
        return processor.getAnswer();
    }

    public String orderBook(){
        bookApi.orderBook(selectedBook).enqueue(processor);

        return processor.getAnswer();
    }
}
