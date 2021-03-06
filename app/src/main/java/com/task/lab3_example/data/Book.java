package com.task.lab3_example.data;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Book {
    @SerializedName("id")
    long id;

    @SerializedName("author")
    String author;

    @SerializedName("title")
    String title;

    @SerializedName("description")
    String description;

    public Book(String title,String author, String description)throws Exception{
        Objects.requireNonNull(title,"incorrect title");
        Objects.requireNonNull(author,"incorrect author");
        Objects.requireNonNull(description,"incorrect description");
        if(title.isEmpty() || author.isEmpty() || description.isEmpty())
            throw new Exception("field cannot be empty");
        this.title = title;
        this.author = author;
        this.description = description;
        this.id = 0;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
