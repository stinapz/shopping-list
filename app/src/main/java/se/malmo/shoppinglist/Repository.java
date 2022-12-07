package se.malmo.shoppinglist;

import java.util.ArrayList;
import java.util.List;

public interface Repository {

    ListItem findItemById(int id);

    ArrayList<ListItem> findAllItems();



    void deleteItem(int id);
    void save(ListItem listItems);
    void edit(ListItem listItems);
}
