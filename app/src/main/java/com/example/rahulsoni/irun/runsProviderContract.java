package com.example.rahulsoni.irun;

import android.net.Uri;

/**
 * Created by Rahul Soni on 05/12/2017.
 */

public final class runsProviderContract {

    //Class just exists to define some things

    public static final String AUTHORITY = "com.example.rahulsoni.ainsleysappetizers.MyProvider";

    public static final Uri MYLIST_URI = Uri.parse("content://"+AUTHORITY+"/myList");
    public static final String _ID = "_id";
    public static final String DATETIME = "dateTime";
    public static final String DISTANCE = "distance";

}
