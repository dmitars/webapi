package com.task.lab3_example.tabs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.task.lab3_example.activity.BookActivity;
import com.task.lab3_example.activity.MainActivity;
import com.task.lab3_example.R;
import com.task.lab3_example.adapters.BookGridAdapter;
import com.task.lab3_example.data.Book;
import com.task.lab3_example.activity.BooksInterface;
import com.task.lab3_example.managers.DataManager;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class MainTab extends Fragment implements BooksInterface {
    private final MainActivity mainContext;
    Button btnAdd;
    private GridView gridView;
    private DataManager dataManager;
    private BookGridAdapter adapter;

    public MainTab(Context context){
        this.mainContext = (MainActivity)context;
    }

    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        btnAdd = root.findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(view-> startAddBookDialog());

        dataManager = DataManager.getInstance(this);
        dataManager.loadBooks();
        //books = getListData();

        gridView = root.findViewById(R.id.gridView);
       // showBooks(books);
        //gridView.setAdapter(new BookGridAdapter(mainContext, books));

        // When the user clicks on the GridItem
        gridView.setOnItemClickListener((a, v, position, id) -> {
            Object o = gridView.getItemAtPosition(position);
            if(o == null)
                return;
            DataManager.selectedBook = (Book) o;
            Intent intent = new Intent(mainContext, BookActivity.class);
            startActivity(intent);
        });

        gridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            //     Void isScrollStop=false;
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount){

            }
        });
        return root;
    }

    public void showBooks(List<Book>books){
        adapter = new BookGridAdapter(mainContext, books);
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void addBook(Book book) {
        adapter.addBook(book);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void removeBook(Book book) {
        adapter.removeBook(book);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void updateBook(Book book){
        adapter.updateBook(book);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {
        Toast.makeText(mainContext,getString(R.string.functionNotPaidException),Toast.LENGTH_SHORT).show();
    }


    private void startAddBookDialog(){
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
                SharedPreferences preferences = mainContext.getSharedPreferences("token",MODE_PRIVATE);
                String token = preferences.getString("token","");
                //books.add(book);
                dataManager.addBook(book,token);
                //updateBooks();
            } catch (Exception e) {
                Toast.makeText(mainContext, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }).create().show();
    }

    private List<Book>getListData(){
        List<Book>books = new ArrayList<>();
        for(int i = 0;i<15;i++) {
            try {
                books.add(new Book("title"+i,"author"+i,"description"+i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return books;
    }
}
