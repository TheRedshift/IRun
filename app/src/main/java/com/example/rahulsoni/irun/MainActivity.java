package com.example.rahulsoni.irun;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DBHelper dbHelper;

    SQLiteDatabase db;

    SimpleCursorAdapter todoAdapter;

    Cursor todoCursor;

    Boolean isWorking = false;

    //Response codes for the stats and info activities
    static final int STATS = 1;

    static final int INFO = 2;

    boolean ReceiverActive = false;

    MyReceiver myReceiver;

    MyService.MyBinder localService = null;

    boolean mBound = false;


    private class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            updateUI(intent);
            long temp = intent.getLongExtra("total", 0);
            Log.d("g53mdp", String.valueOf(temp));

        }
    }



    private void updateUI(Intent intent) {

        long date = intent.getLongExtra("date", 0);
        float distance = intent.getFloatExtra("distance", 0);
        double time = intent.getDoubleExtra("time", 0);



        ContentValues newValues = new ContentValues();
        newValues.put(runsProviderContract.DATE, date);
        newValues.put(runsProviderContract.DISTANCE, distance);
        newValues.put(runsProviderContract.TIME, time);


        //Database stuff is done through the provider
        getContentResolver().insert(runsProviderContract.MYLIST_URI, newValues);



    }

    @Override
    protected void onStart() {

        super.onStart();

        if (!ReceiverActive) {
            if (myReceiver == null){
                myReceiver = new MyReceiver();
                registerReceiver(myReceiver, new IntentFilter("com.example.rahulsoni.irun.test"));
                ReceiverActive = true;
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (ReceiverActive) {
            unregisterReceiver(myReceiver);
            myReceiver = null;
            ReceiverActive = false;
        }
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
            localService = (MyService.MyBinder) service;
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            localService = null;
            mBound = false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service)
            {
                localService = (MyService.MyBinder) service;
                mBound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                localService = null;
                mBound = false;
            }
        };

        if (savedInstanceState != null) {
            isWorking = savedInstanceState.getBoolean("isWorking");
       }

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        Chronometer clocky = findViewById(R.id.chronometer2);

        clocky.start();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //startService(new Intent(this,MyService .class));

        this.bindService(new Intent(this, MyService.class),
                serviceConnection, Context.BIND_AUTO_CREATE);
        mBound = true;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        this.unbindService(serviceConnection);
        mBound = false;
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("isWorking", isWorking);

    }

    public void onToggleButtonClick(View view) {

        Button b1 = findViewById(R.id.button1);

        if (!isWorking){
            Log.d("g53mdp", "Toggle button pressed, it is now working");
            isWorking = true;
            localService.startRunning();
            b1.setText("Stop Running");
        }
        else{
            Log.d("g53mdp", "Toggle button pressed, it is now not working");
            isWorking = false;
            localService.stopRunning();
            b1.setText("Start Running!");
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_run) {
            Log.d("g53mdp", "Run button pressed");
        } else if (id == R.id.nav_stats) {
            Log.d("g53mdp", "Stats button pressed");
            Intent intent = new Intent(MainActivity.this, StatsActivity.class);
            startActivityForResult(intent, STATS);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
