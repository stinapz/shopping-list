package se.malmo.shoppinglist;

import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

// interface
// för att kunna använda samma metoder men på olika sätt, koppla av och koppla på olika implementeringar
// publika metoder
public interface Repository {

    ListItem findItemById(int id);
    ArrayList<ListItem> findAllItems();
    void deleteItem(int id);
    void save(ListItem listItems);
    void deleteAll();
}
