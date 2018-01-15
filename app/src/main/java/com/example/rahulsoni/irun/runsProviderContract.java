package com.example.rahulsoni.irun;

import android.net.Uri;

/**
 * Created by Rahul Soni on 05/12/2017.
 */

public final class runsProviderContract {

    //Class just exists to define some things

    static final String[] RUN_PROJECTION = new String[] {
            runsProviderContract._ID,
            runsProviderContract.DATE,
            runsProviderContract.DISTANCE,
            runsProviderContract.TIME

    };

    public static final String AUTHORITY = "com.example.rahulsoni.irun.runsProvider";

    public static final Uri MYLIST_URI = Uri.parse("content://"+AUTHORITY+"/myList");

    public static final String _ID = "_id";

    public static final String DATE = "date";
    public static final String DISTANCE = "distance";
    public static final String TIME = "time";

}
