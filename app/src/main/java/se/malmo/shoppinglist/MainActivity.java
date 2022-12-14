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

public class MainActivity extends AppCompatActivity implements ItemAdapter.AdapterCallback {
    RecyclerView recyclerView;
    ItemAdapter adapter;
    Repository repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repo = SqliteRepo.getInstance(getApplicationContext());

        recyclerView = findViewById(R.id.recyclerViewID);

        adapter = new ItemAdapter(this, repo.findAllItems());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onBtnAddClick(View view) {
        Intent intent = new Intent(this, EditListActivity.class);
        startActivity(intent);
    }

    public void onBtnDeleteListClick(View view){
        if(adapter.items.size() == 0) {
            Toast.makeText(getApplicationContext(), R.string.List_is_aldready_empty, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), R.string.Deleted_list, Toast.LENGTH_SHORT).show();
            repo.deleteAll();
            navigateBackToMain();
        }
    }

    private void navigateBackToMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void updateIsChecked(ListItem item) {
        try {
            repo.save(item);
        } catch (ClassCastException e) {
        }
    }
}