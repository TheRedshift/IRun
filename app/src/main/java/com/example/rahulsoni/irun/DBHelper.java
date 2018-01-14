package com.example.rahulsoni.irun;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Rahul Soni on 14/01/2018.
 */

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "rahulDB", null, 1  );
    }


    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE runs (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "date LONG(64), " +
                "distance LONG(64)" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS runs;");
        onCreate(db);
    }

}
