package com.example.rahulsoni.irun;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;


import static com.example.rahulsoni.irun.MainActivity.STATS;
import static com.example.rahulsoni.irun.MainActivity.INFO;


public class StatsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        ListView RunnersListView = findViewById(R.id.listView);

        ArrayList<Runners> RunnersList = new ArrayList<>();

        runsProvider handler = new runsProvider();

        Cursor cursor = getContentResolver().query(runsProviderContract.MYLIST_URI,
                runsProviderContract.RUN_PROJECTION, null, null,
                null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
                RunnersList.add(new Runners(cursor.getInt(cursor.getColumnIndex("_id")),
                        cursor.getFloat(cursor.getColumnIndex("distance")),
                        cursor.getInt(cursor.getColumnIndex("time"))));
                cursor.moveToNext();
        }


        CustomListRun RunnersAdapter = new CustomListRun(this, RunnersList);

        RunnersListView.setAdapter(RunnersAdapter);

        //The following code populates the fields if they existed in the bundle, otherwise leaves them blank
        Bundle bundle = getIntent().getExtras();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_run) {
            Log.d("g53mdp", "Run button pressed");
            Intent intent = new Intent(StatsActivity.this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_stats) {
            Log.d("g53mdp", "Stats button pressed");
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

