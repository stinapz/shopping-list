package se.malmo.shoppinglist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

import java.util.ArrayList;

// denna hör ihop med interfacet och här säger vi vad dessa metoderna gör för någonting.
public class SqliteRepo implements Repository {
    private static final String TABLE_NAME = "Items";
    private SQLiteOpenHelper sqlite;

    private static SqliteRepo instance = null;
    public static SqliteRepo getInstance(Context context){
        if(instance == null)
            instance = new SqliteRepo(context);

        return instance;
    }
    private SqliteRepo(Context context){
        sqlite = DbHelper.getInstance(context);
    }

    @Override
    public ListItem findItemById(int id) {
        SQLiteDatabase db = sqlite.getReadableDatabase();
        String query = "SELECT * FROM items WHERE id = " + id;

        Cursor cursor = db.rawQuery(query, null);

        // Värdet är bool, om bool är sant görs alternativ 1, annars alternativ två.
        // Dessa delas av med :
        ListItem listItem = cursor.moveToFirst()
                ? new ListItem()
                .setId(cursor.getInt(0))
                .setItem(cursor.getString(1))
                .setIsChecked(cursor.getInt(2))
                : null;

        cursor.close(); // vi behöver stänga cursorn när vi är klara
        return listItem;
    }

    // gör arraylista av allt som finns i databasen.
    @Override
    public ArrayList<ListItem> findAllItems() {
        SQLiteDatabase db = sqlite.getReadableDatabase();
        ArrayList<ListItem> listItems = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            ListItem listItem = new ListItem()
                    .setId(cursor.getInt(0))
                    .setItem(cursor.getString(1))
                    .setIsChecked(cursor.getInt(2));

            listItems.add(listItem);
        }
        cursor.close();
        return listItems;
    }

    @Override
    public void deleteItem(int id) {
        SQLiteDatabase db = sqlite.getWritableDatabase();
        String[] args = getWhereArgs(id);
        db.delete(TABLE_NAME, "id = ?", args);
        db.close();
    }

    @Override
    public void save(ListItem listItem) {
        if(listItem.getId() == 0)
            insertItem(listItem);
        else
            updateItem(listItem);
    }

    @Override
    public void deleteAll() {
        SQLiteDatabase db = this.sqlite.getReadableDatabase();
        db.delete(TABLE_NAME,null,null);
        db.execSQL("delete FROM "+ TABLE_NAME);
        db.close();
    }


    private void insertItem(ListItem listItem){
        SQLiteDatabase db = sqlite.getWritableDatabase();
        ContentValues c = getContentValues(listItem);
        db.insert(TABLE_NAME, null, c);
        db.close();
    }

    private void updateItem(ListItem listItem){
        SQLiteDatabase db = sqlite.getWritableDatabase();

        ContentValues c = getContentValues(listItem);
        String[] whereArgs = getWhereArgs(listItem.getId());

        db.update(TABLE_NAME, c, "id = ?", whereArgs);
        db.close();
    }

    @NonNull
    private ContentValues getContentValues(ListItem listItem) {

        ContentValues c = new ContentValues();
        c.put("Item", listItem.getItem());
        c.put("IsChecked", listItem.getIsChecked());
        return c;
    }

    @NonNull
    private String[] getWhereArgs(int id) {
        String[] whereArgs = {String.valueOf(id)};
        return whereArgs;
    }

}


