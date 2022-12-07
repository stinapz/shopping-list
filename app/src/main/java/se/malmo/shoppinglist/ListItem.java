package se.malmo.shoppinglist;

public class ListItem {

    private int id;
    private String item;

    public ListItem() {
        item = "";
    }

    public ListItem(int id, String item) {
        this.id = id;
        this.item = item;

    }

    public int getId() {
            return id;
        }

    public ListItem setId(int id) {
        this.id = id;
        return this;
    }
    public String getItem() {
        return item;
    }

    public ListItem setItem(String item) {
        this.item = item;
        return this;
    }

}
