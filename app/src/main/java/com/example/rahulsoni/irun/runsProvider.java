package com.example.rahulsoni.irun;

import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;

import android.database.Cursor;
import android.database.SQLException;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by Rahul Soni on 14/01/2018.
 */

public class runsProvider extends ContentProvider {

    DBHelper dbHelper;

    /*

    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(MyProviderContract.AUTHORITY, "myList", 1);
        uriMatcher.addURI(MyProviderContract.AUTHORITY, "myList/#", 2);
    }

    */

    @Override
    public boolean onCreate() {
        Log.d("g53mdp", "contentprovider oncreate");
        //Gives us a DBhelper for DB helping
        this.dbHelper = new DBHelper(this.getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //Only one table so nice and simple
        String tableName = "myList";

        return db.query(tableName, null, selection, selectionArgs, null, null, sortOrder);
    }

    @Override
    public String getType(Uri uri) {
        if (uri.getLastPathSegment()==null)
        {
            return "vnd.android.cursor.dir/MyProvider.data.text";
        }
        else
        {
            return "vnd.android.cursor.item/MyProvider.data.text";
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String tableName = "myList";

        long id = db.insert(tableName, null, values);
        Uri nu = ContentUris.withAppendedId(uri, id);
        Log.d("g53mdp", nu.toString());
        return nu;

    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String tableName = "myList";

        //We only ever update based on _id so we can hardcode this
        db.update(tableName, contentValues, "_id=?" , strings);

        return 0;
    }

}