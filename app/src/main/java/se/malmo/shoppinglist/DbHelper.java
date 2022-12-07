package se.malmo.shoppinglist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

        public static final String DB_NAME = "Shopping.db";
        public static final int VERSION = 1;
        public static final String TABLE_NAME = "Items";

        public DbHelper(@Nullable Context context ) {
            super(context, DB_NAME, null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String query =
                    "CREATE TABLE Items (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "item TEXT," +
                            "isChecked BOOL" +
                            ")";

            db.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS Items");
            onCreate(db);
        }
    }
