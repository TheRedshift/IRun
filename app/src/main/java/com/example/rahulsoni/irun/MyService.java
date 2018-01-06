package com.example.rahulsoni.irun;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Rahul Soni on 06/01/2018.
 */

public class MyService extends Service {

    private LocationManager mLocationManager = null;

    private class MyLocationListener implements LocationListener {

        Location myLocation;

        public void MyLocationListener(String provider)
        {
            Log.e("G53", "LocationListener " + provider);
            myLocation = new Location(provider);
        }
        @Override
        public void onLocationChanged(Location location) {
            Log.d("g53mdp", location.getLatitude() + " " + location.getLongitude());
            Log.d("g53mdp", String.valueOf(location.distanceTo(location)));
        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // information about the signal, i.e. number of satellites
            Log.d("g53mdp", "onStatusChanged: " + provider + " " + status);
        }
        @Override
        public void onProviderEnabled(String provider) {
            // the user enabled (for example) the GPS
            Log.d("g53mdp", "onProviderEnabled: " + provider);
        }
        @Override
        public void onProviderDisabled(String provider) {
            // the user disabled (for example) the GPS
            Log.d("g53mdp", "onProviderDisabled: " + provider);
        }
    }


    @Override
    public IBinder onBind(Intent arg0)
    {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.e("g53mdp", "onStartCommand");
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public void onCreate()
    {
        Log.e("g53mdp", "onCreate");
        LocationManager locationManager =
                (LocationManager)getBaseContext().getSystemService(Context.LOCATION_SERVICE);
        LocationListener myLocationListener = new MyLocationListener() {
        };


        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    5, // minimum time interval between updates
                    5, // minimum distance between updates, in metres
                    myLocationListener);
        } catch(SecurityException e) {
            Log.d("g53mdp", e.toString());
        }

    }

}
