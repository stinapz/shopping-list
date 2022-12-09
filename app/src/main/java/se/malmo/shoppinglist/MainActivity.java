package se.malmo.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ItemAdapter itemAdapter;
    DbHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("Hello World");

        dbHelper = DbHelper.getInstance(this);
        /*
        recyclerView = findViewById(R.id.rv_shopping_lists);
        ArrayList<ListItem> items = dbHelper.getAll();
        bookAdapter = new BookAdapter(this, books);

        // attach the adapter to the recycler view
        booksListView.setAdapter(bookAdapter);
        booksListView.setLayoutManager(new LinearLayoutManager(this));
        */
    }


    public void onBtnAddClick(View view) {
        Intent intent = new Intent(this, EditListActivity.class);
        startActivity(intent);
    }
}