package se.malmo.shoppinglist;

import java.util.ArrayList;
import java.util.List;

public interface Repository {

    ArrayList<ListItem> findAllItems();

    void deleteItem(int id);
    void save(ListItem listItem);
    void edit(ListItem listItem);
}
