package com.task.lab3_example.managers;

import androidx.annotation.NonNull;

import com.task.lab3_example.api.BookApi;
import com.task.lab3_example.data.Book;
import com.task.lab3_example.activity.BooksInterface;

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

    public void addBook(Book book, String token){
        bookApi.addBook(book,token).enqueue(new Callback<Book>() {
            @Override
            public void onResponse(@NonNull Call<Book> call, @NonNull Response<Book> response) {
                //books.addAll(response.body());
                if(response.body()!=null)
                    booksInterface.addBook(response.body());
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

    public String updateBook(Book book, String token){
        bookApi.updateBook(book,token).enqueue(new Callback<Book>() {
            @Override
            public void onResponse(@NonNull Call<Book> call, @NonNull Response<Book> response) {
                //books.addAll(response.body());
                if(response.body()!=null)
                    booksInterface.updateBook(response.body());
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {

            }
        });
        return processor.getAnswer();
    }

    public String removeBook(String token){
        bookApi.removeBook(selectedBook.getId(),token).enqueue(new Callback<Book>() {
            @Override
            public void onResponse(@NonNull Call<Book> call, @NonNull Response<Book> response) {
                //books.addAll(response.body());
                if(response.body()!=null)
                    booksInterface.removeBook(response.body());
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {

            }
        });
        return processor.getAnswer();
    }

    public String orderBook(String token){
        bookApi.orderBook(selectedBook,token).enqueue(processor);
        loadBooks();
        return processor.getAnswer();
    }
}
