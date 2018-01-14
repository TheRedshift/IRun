package com.example.rahulsoni.irun;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "rahulDB", null, 1  );
    }

    //Made recipe section longer because who has a 128 character recipe?
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE myList (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "dateTime LONG(64), " +
                "distance LONG(64)" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS myList;");
        onCreate(db);
    }

}
