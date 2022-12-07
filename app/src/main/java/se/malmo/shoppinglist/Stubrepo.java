package se.malmo.shoppinglist;

import java.util.ArrayList;

public class Stubrepo implements Repository {


    private ArrayList<ListItem> listItems;

    public void StubRepo(){
        listItems = new ArrayList<>();
        listItems.add(new ListItem()
                .setId(1)
                .setItem("Mjölk")
                .setIsChecked(true)
        );
        listItems.add(new ListItem()
                .setId(2)
                .setItem("Ost")
                .setIsChecked(false)
        );
    }
    @Override
    public ArrayList<ListItem> findAllItems() {
        return listItems;
    }

    @Override
    public void deleteItem(int id) {

    }

    @Override
    public void save(ListItem listItem) {

    }



    @Override
    public void edit(ListItem listItem) {

    }
}


