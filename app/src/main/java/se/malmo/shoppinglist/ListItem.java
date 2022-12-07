package se.malmo.shoppinglist;

public class ListItem {

    //fields
    private int id;
    private String item;
    private Boolean isChecked;

    //Konstruktor
    public ListItem() {
        item = "";
    }

    public ListItem(int id, String item, boolean isChecked) {
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
    public boolean getIsChecked(){
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
