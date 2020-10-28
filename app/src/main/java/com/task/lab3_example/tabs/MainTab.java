package com.task.lab3_example.tabs;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.task.lab3_example.activity.BookActivity;
import com.task.lab3_example.activity.MainActivity;
import com.task.lab3_example.R;
import com.task.lab3_example.adapters.BookGridAdapter;
import com.task.lab3_example.data.Book;
import com.task.lab3_example.data.DataManager;

import java.util.ArrayList;
import java.util.List;

public class MainTab extends Fragment {
    private final MainActivity mainContext;
    Button btnAdd;
    private List<Book>books;
    private GridView gridView;
    private DataManager dataManager;

    public MainTab(Context context){
        this.mainContext = (MainActivity)context;
    }

    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        btnAdd = root.findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(view->addBook());

        dataManager = DataManager.getInstance();
        books = dataManager.loadBooks();
        //books = getListData();
        gridView = root.findViewById(R.id.gridView);
        gridView.setAdapter(new BookGridAdapter(mainContext, books));

        // When the user clicks on the GridItem
        gridView.setOnItemClickListener((a, v, position, id) -> {
            Object o = gridView.getItemAtPosition(position);
            DataManager.selectedBook = (Book) o;
            Intent intent = new Intent(mainContext, BookActivity.class);
            startActivity(intent);
        });
        return root;
    }

    private void updateBooks(){
        //books = dataManager.loadBooks();
        gridView.setAdapter(new BookGridAdapter(mainContext, books));
    }


    private void addBook(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(mainContext);
        dialog.setTitle("Добавить");
        dialog.setMessage("Введите параметры новой книги");

        LayoutInflater inflater = LayoutInflater.from(mainContext);
        View add_book_window = inflater.inflate(R.layout.add_book_window,null);
        dialog.setView(add_book_window);

        final MaterialEditText etTitle = add_book_window.findViewById(R.id.title_field);
        final MaterialEditText etAuthor = add_book_window.findViewById(R.id.author_field);
        final MaterialEditText etDescription = add_book_window.findViewById(R.id.description_field);

        dialog.setNegativeButton("Отмена", (dialogInterface, i) -> dialogInterface.dismiss());

        dialog.setPositiveButton("Добавить", (dialogInterface, i) -> {
            String title = etTitle.getText().toString();
            if (TextUtils.isEmpty(title)) {
                Toast.makeText(mainContext, "Название не задано", Toast.LENGTH_SHORT).show();
                return;
            }

            String author = etAuthor.getText().toString();
            if (TextUtils.isEmpty(author)) {
                Toast.makeText(mainContext, "Автор не задан", Toast.LENGTH_SHORT).show();
                return;
            }

            String description = etDescription.getText().toString();
            if (TextUtils.isEmpty(description)) {
                Toast.makeText(mainContext, "Описание не задано", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                Book book = new Book(title,author,description);
                books.add(book);
                dataManager.addBook(book);
                updateBooks();
            } catch (Exception e) {
                Toast.makeText(mainContext, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }).create().show();
    }

    private List<Book>getListData(){
        List<Book>books = new ArrayList<>();
        for(int i = 0;i<20;i++) {
            try {
                books.add(new Book("title"+i,"author"+i,"description"+i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return books;
    }
}
