package se.malmo.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ItemAdapter adapter;
    Repository repo;
    ItemAdapter itemAdapter;
    DbHelper dbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        repo = SqliteRepo.getInstance(getApplicationContext());

        recyclerView = findViewById(R.id.rv_shopping_lists);

        adapter = new ItemAdapter(this, repo.findAllItems());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    
    public void onBtnAddClick(View view) {
        Intent intent = new Intent(this, EditListActivity.class);
        startActivity(intent);
    }
}