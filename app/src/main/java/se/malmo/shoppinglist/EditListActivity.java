package se.malmo.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class EditListActivity extends AppCompatActivity implements ItemAdapter.AdapterCallback {
    RecyclerView listView;
    ItemAdapter adapter;
    private int itemId;
    Repository itemRepo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list);

        itemRepo = SqliteRepo.getInstance(getApplicationContext());

        listView = findViewById(R.id.rv_list_items);

        adapter = new ItemAdapter(this, itemRepo.findAllItems());
        listView.setAdapter(adapter);
        listView.setLayoutManager(new LinearLayoutManager(this));

        ListItem item = getItemFromIntent();
        itemId = item.getId();
        initViewFromItem(item);

    }



    public void onBtnSaveClick(View view) {

        ListItem item = new ListItem(itemId, getTextFromView(R.id.txt_edit_item), 0);

        if (Objects.equals(item.getItem(), ""))
            Toast.makeText(getApplicationContext(), R.string.Error_Adding_Item, Toast.LENGTH_SHORT).show();
        else {
            itemRepo.save(item);
            Toast.makeText(this, R.string.Item_added, Toast.LENGTH_SHORT).show();
            navigateToEditList();
        }
    }

    public void onBtnRemoveClick(View view) {
        itemRepo.deleteItem(itemId);
        navigateToEditList();
        Toast.makeText(this, R.string.deleted_item, Toast.LENGTH_SHORT).show();
    }


    private ListItem getItemFromIntent(){
        Intent intent = getIntent();
        int itemId = intent.getIntExtra("id", 0);

        ListItem item = itemRepo.findItemById(itemId);
        // Om varan har värde null i db, returnera en ny.
        return item == null ? new ListItem() : item;
    }

    private void navigateBackToMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void navigateToEditList(){
        Intent intent = new Intent(this, EditListActivity.class);
        startActivity(intent);
    }

    private void initViewFromItem(ListItem item) {
        setText(R.id.txt_edit_item, item.getItem());
    }

    private void setText(int resId, String value) {
        TextView view = findViewById(resId);
        view.setText(value);
    }

    private String getTextFromView(int resId) {
        TextView view = findViewById(resId);
        return view.getText().toString();
    }

    @Override
    public void updateIsChecked(ListItem item) {
        try {
            itemRepo.save(item);
        } catch (ClassCastException e) {
            // do something
        }
    }
}