package com.task.lab3_example.managers;

import com.task.lab3_example.data.Book;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookCallProcessor implements Callback<Book> {
    String answer;

    public String getAnswer() {
        return answer;
    }

    @Override
    public void onResponse(Call<Book> call, Response<Book> response) { ;
        answer = response.toString();
    }

    @Override
    public void onFailure(Call<Book> call, Throwable t) {
        answer = "request failed";
    }

}
