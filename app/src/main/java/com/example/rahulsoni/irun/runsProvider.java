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

public class runsProvider extends ContentProvider {

    private DBHelper dbHelper;



    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(runsProviderContract.AUTHORITY, "myList", 1);
        uriMatcher.addURI(runsProviderContract.AUTHORITY, "myList/#", 2);
    }



    @Override
    public boolean onCreate() {
        Log.d("g53mdp", "runsProvider created");
        this.dbHelper = new DBHelper(this.getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        switch (uriMatcher.match(uri)) {
            case 2:
                selection = "_ID = " + uri.getLastPathSegment();
            case 1:
                return db.query("myList", projection, selection, selectionArgs, null, null, sortOrder);
            default:
                return null;
        }
    }

    @Override
    public String getType(Uri uri) {
            return "";
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Log.d("MyList, entry is", uri.toString() + " " + uriMatcher.match(uri));

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String table;

        switch (uriMatcher.match(uri)) {
            case 1:
                table = "myList";
                break;
            default:
                table = "myList";
                break;
        }

        long id = db.insert(table, null, values);
        db.close();
        Uri nu = ContentUris.withAppendedId(uri, id);

        Log.d("RecipeBook", nu.toString());

        getContext().getContentResolver().notifyChange(nu, null);

        return nu;
    }

    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d("MyList, entry is ", uri.toString() + " " + uriMatcher.match(uri));

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String table;
        String[] Args = new String[]{
                null
        };
        String where;

        switch (uriMatcher.match(uri)) {
            case 2:
                table = "MyList";
                where = "_ID = ?";
                Args[0] = uri.getLastPathSegment();
                break;
            default:
                return 0;
        }

        int rowsAffected = db.delete(table, where, Args);
        db.close();
        return rowsAffected;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d("MyList, entry is", uri.toString() + " " + uriMatcher.match(uri));

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String table;
        String[] Args = new String[]{
                null
        };
        String where;

        switch (uriMatcher.match(uri)) {
            case 2:
                table = "MyList";
                where = "_ID = ?";
                Args[0] = uri.getLastPathSegment();
                break;
            default:
                return 0;
        }

        int rowsAffected = db.update(table, values, where, Args);
        db.close();
        return rowsAffected;
    }

}