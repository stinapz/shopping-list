package se.malmo.shoppinglist;

public class ListItem {

    //Fields
    private int id;
    private String item;
    private int isChecked = 0;

    //Konstruktor
    public ListItem() {
        item = "";
        isChecked = 0;
    }

    public ListItem(int id, String item, int isChecked) {
        this.id = id;
        this.item = item;
        this.isChecked = isChecked;
    }

    public int getId() {
            return id;
    }

    public ListItem setId(int id) {
        this.id = id;
        return this;
    }
    public int getIsChecked(){
        return isChecked;
    }

    public ListItem setIsChecked(int isChecked){
        this.isChecked = isChecked;
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
