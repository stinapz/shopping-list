package se.malmo.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ListView;

public class EditListActivity extends AppCompatActivity {
    RecyclerView listView;
    ItemAdapter adapter;
    Repository repo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list);

        repo = SqliteRepo.getInstance(getApplicationContext());

        listView = findViewById(R.id.rv_list_items);

        ItemAdapter ListView = new ItemAdapter(this, repo.findAllItems());
        listView.setAdapter(adapter);
        listView.setLayoutManager(new LinearLayoutManager(this));

    }
}