package com.task.lab3_example.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.task.lab3_example.R;
import com.task.lab3_example.data.Book;
import com.task.lab3_example.managers.DataManager;

public class BookActivity extends AppCompatActivity {
    EditText etTitle;
    EditText etAuthor;
    EditText etDescription;

    Button btnUpdate;
    Button btnOrder;
    Button btnRemove;

    DataManager dataManager;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_activity);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnOrder = findViewById(R.id.btnOrder);
        btnRemove = findViewById(R.id.btnRemove);

        btnUpdate.setOnClickListener(event -> updateBook());
        btnOrder.setOnClickListener(event -> orderBook());
        btnRemove.setOnClickListener(event -> removeBook());

        etTitle = findViewById(R.id.etTitle);
        etAuthor = findViewById(R.id.etAuthor);
        etDescription = findViewById(R.id.etDescription);


        etTitle.setText(DataManager.selectedBook.getTitle());
        etAuthor.setText(DataManager.selectedBook.getAuthor());
        etDescription.setText(DataManager.selectedBook.getDescription());

        sharedPreferences = getSharedPreferences("tokens",MODE_PRIVATE);

        dataManager = DataManager.getInstance(null);
    }

    private void updateBook() {
        String bookTitle = etTitle.getText().toString();
        String bookAuthor = etAuthor.getText().toString();
        String bookDescription = etDescription.getText().toString();

        Book currentBook = DataManager.selectedBook;
        currentBook.setAuthor(bookAuthor);
        currentBook.setTitle(bookTitle);
        currentBook.setDescription(bookDescription);
        try {
            String token = sharedPreferences.getString("UpdateBook","");
            dataManager.updateBook(currentBook,token);
            finish();
        } catch (Exception e) {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    private void orderBook() {
        String token = sharedPreferences.getString("OrderBook","");
        dataManager.orderBook(token);
        finish();
    }

    private void removeBook() {
        String token = sharedPreferences.getString("DeleteBook","");
        dataManager.removeBook(token);
        finish();
    }
}
