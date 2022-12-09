package se.malmo.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class EditListActivity extends AppCompatActivity {
    RecyclerView listView;
    ItemAdapter adapter;
    Repository repo;
    private int itemId;
    Repository itemRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list);

        itemRepo = SqliteRepo.getInstance(getApplicationContext());

    }

    public void onBtnSaveClick(View view) {
        ListItem item = new ListItem(itemId,getTextFromView(R.id.txt_edit_item),0);



        itemRepo.save(item);
        navigateBackToMain();
    }

    public void onBtnRemoveClick(View view) {
        itemRepo.deleteItem(itemId);
        navigateBackToMain();
    }

    public void onBtnDeleteListClick(View view){
        itemRepo.deleteAll();
        navigateBackToMain();
    }



    private ListItem getItemFromIntent(){
        Intent intent = getIntent();
        int itemId = intent.getIntExtra("id", 0);

        ListItem item = repo.findItemById(itemId);
        // if the book does not exist in the db (null) then return a new one
        return item == null ? new ListItem() : item;
    }

    private void navigateBackToMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void initViewFromItem(ListItem item) {
        setText(R.id.txt_edit_item, item.getItem());

    }



    private void setText(int resId, String value) {
        TextView view = findViewById(resId);
        view.setText(value);
    }
    private void setText(int resId, int value) {
        setText(resId, String.valueOf(value));
    }

    private String getTextFromView(int resId) {
        TextView view = findViewById(resId);
        return view.getText().toString();
    }
    private int getIntFromView(int resId) {
        return Integer.parseInt(getTextFromView(resId));
    }

    private void setViewVisible(int resId, boolean visible) {
        View view = findViewById(resId);
        int visibility = visible ? View.VISIBLE : View.GONE;
        // GONE does not render the button
        // INVISIBLE reserves the space but does not show the button
        view.setVisibility(visibility);
    }


}