package com.example.rahulsoni.irun;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    //Response codes for the stats and info activities
    static final int STATS = 1;

    static final int INFO = 2;

    boolean ReceiverActive = false;

    MyReceiver myReceiver;

    private class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            updateUI(intent);
        }
    }

    private void updateUI(Intent intent) {
        // TODO update ui
        Toast.makeText(this, "test", Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onResume() {

        super.onResume();

        if (!ReceiverActive) {
            if (myReceiver == null)
                myReceiver = new MyReceiver();
            registerReceiver(myReceiver, new IntentFilter("com.example.rahulsoni.irun.MyBroadcast"));
            ReceiverActive = true;
        }
    }

    @Override
    protected void onPause() {

        super.onPause();

        if (ReceiverActive) {
            unregisterReceiver(myReceiver);
            myReceiver = null;
            ReceiverActive = false;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        startService(new Intent(this,MyService .class));
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

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    */

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
        } else if (id == R.id.nav_info) {
            Log.d("g53mdp", "Info button pressed");
            Intent intent = new Intent(MainActivity.this, InfoActivity.class);
            startActivityForResult(intent, INFO);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
