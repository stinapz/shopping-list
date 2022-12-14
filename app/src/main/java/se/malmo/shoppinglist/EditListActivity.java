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

// detta är den andra viewn, med knapparna delete, save och där man kan editera varan.
public class EditListActivity extends AppCompatActivity implements ItemAdapter.AdapterCallback {
    RecyclerView listView;
    ItemAdapter adapter;
    private int itemId;
    Repository itemRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list);

        // skapar en sqliterepo -> en koppling till databasen
        // vi säger att vårt repo är sqliterepot
        itemRepo = SqliteRepo.getInstance(getApplicationContext());

        // hittar recyklerviewn
        listView = findViewById(R.id.rv_list_items);

        // adaptern binder ihopa datan med recyklerviewn
        adapter = new ItemAdapter(this, itemRepo.findAllItems());
        listView.setAdapter(adapter);
        listView.setLayoutManager(new LinearLayoutManager(this));

        //detta behövs för att få med varan från main till textviewn för att kunna editera
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

    //intent är byte av sida, man tar med sig information från tex main till ny view.
    private ListItem getItemFromIntent(){
        Intent intent = getIntent();
        int itemId = intent.getIntExtra("id", 0);

        ListItem item = itemRepo.findItemById(itemId);
        // Om varan har värde null i db, returnera en ny.
        return item == null ? new ListItem() : item;
    }

    private void navigateToEditList(){
        Intent intent = new Intent(this, EditListActivity.class);
        startActivity(intent);
    }

    // sätter texten i textrutan när man har klickat på en vara.
    // använder sig av metoden setText
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

    // skickar tillståndet på Checkedboxen till databasen
    @Override
    public void updateIsChecked(ListItem item) {
        try {
            itemRepo.save(item);
        } catch (ClassCastException e) {
            // do something
        }
    }
}