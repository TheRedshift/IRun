package com.example.rahulsoni.irun;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Rahul Soni on 14/01/2018.
 */


public class MyBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context ctxt, Intent i) {


        System.out.println("received");
        Log.d("g53mdp", "broadcast");
    }
}