package com.task.lab3_example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.task.lab3_example.R;
import com.task.lab3_example.data.Book;
import com.task.lab3_example.data.DataManager;

public class BookActivity extends AppCompatActivity {
    EditText etTitle;
    EditText etAuthor;
    EditText etDescription;

    Button btnUpdate;
    Button btnOrder;
    Button btnRemove;

    DataManager dataManager;

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

        dataManager = DataManager.getInstance(null);
    }

    private void updateBook() {
        String bookTitle = etTitle.getText().toString();
        String bookAuthor = etAuthor.getText().toString();
        String bookDescription = etDescription.getText().toString();
        try {
            Book book = new Book(bookTitle, bookAuthor, bookDescription);
            dataManager.updateBook(book);
            loadBaseActivity();
        } catch (Exception e) {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    private void orderBook() {
        dataManager.orderBook();
        loadBaseActivity();
    }

    private void removeBook() {
        dataManager.removeBook();
        loadBaseActivity();
    }

    private void loadBaseActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
