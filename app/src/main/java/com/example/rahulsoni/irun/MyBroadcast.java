package com.example.rahulsoni.irun;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;

import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by Rahul Soni on 14/01/2018.
 */


public class MyBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context ctxt, Intent i) {


        Bundle b = i.getExtras();

        double lat = b.getDouble("lat");
        double longi = b.getDouble("long");

        String latString = String.valueOf(lat);
        String longString = String.valueOf(longi);

        Toast.makeText(ctxt, latString, Toast.LENGTH_LONG).show();

    }
}