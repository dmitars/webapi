package com.task.lab3_example.data;

import android.provider.ContactsContract;

import androidx.annotation.NonNull;

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
    BooksInterface booksInterface;

    private static DataManager instance;

    private DataManager(BooksInterface booksInterface){
        super();
        this.booksInterface = booksInterface;
        bookApi = retrofit.create(BookApi.class);
        processor = new BookCallProcessor();
    }

    public static DataManager getInstance(BooksInterface booksInterface){
        if(instance == null)
            instance = new DataManager(booksInterface);
        return instance;
    }

    public void addBook(Book book){
        bookApi.addBook(book).enqueue(new Callback<Book>() {
            @Override
            public void onResponse(@NonNull Call<Book> call, @NonNull Response<Book> response) {
                //books.addAll(response.body());
                if(response.body()!=null)
                    System.out.println(response.body().toString());
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {

            }
        });
    }

    public void loadBooks(){
        Call<List<Book>> callBooks = bookApi.getBooks();
        callBooks.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(@NonNull Call<List<Book>> call, @NonNull Response<List<Book>> response) {
                List<Book>books = response.body();
                if(books == null)
                    books = new ArrayList<>();
                booksInterface.showBooks(books);
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public String updateBook(Book book){
        bookApi.updateBook(book).enqueue(processor);
        return processor.getAnswer();
    }

    public String removeBook(){
        bookApi.removeBook(selectedBook.getId()).enqueue(processor);
        return processor.getAnswer();
    }

    public String orderBook(){
        bookApi.orderBook(selectedBook).enqueue(processor);

        return processor.getAnswer();
    }
}
