package se.malmo.shoppinglist;

import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public interface Repository {

    ListItem findItemById(int id);
    ArrayList<ListItem> findAllItems();
    void deleteItem(int id);
    void save(ListItem listItems);
    void deleteAll();
}
