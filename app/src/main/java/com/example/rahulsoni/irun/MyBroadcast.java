package com.example.rahulsoni.irun;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Rahul Soni on 14/01/2018.
 */


public class MyBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context ctxt, Intent i) {


        Toast.makeText(ctxt, "This is my Toast message!",
                Toast.LENGTH_LONG).show();

    }
}