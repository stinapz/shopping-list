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
    SqliteRepo itemRepo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemRepo = SqliteRepo.getInstance(getApplicationContext());

        recyclerView = findViewById(R.id.rv_shopping_lists);

        itemAdapter = new ItemAdapter(this, itemRepo.findAllItems());

        // attach the adapter to the recycler view
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    public void onBtnAddClick(View view) {
        Intent intent = new Intent(this, EditListActivity.class);
        startActivity(intent);
    }
}