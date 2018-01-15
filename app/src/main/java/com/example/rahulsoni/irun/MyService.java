package com.example.rahulsoni.irun;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyService extends Service {


    private LocationManager mLocationManager;

    private LocationListener myLocationListener;

    private IBinder myBinder = new MyBinder();

    public class MyBinder extends Binder {


        public void startRunning() {

            Log.e("g53mdp", "start running");

            mLocationManager =
                    (LocationManager)getBaseContext().getSystemService(Context.LOCATION_SERVICE);
            myLocationListener = new MyLocationListener() {
            };


            try {
                mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                        2, // minimum time interval between updates
                        5, // minimum distance between updates, in metres
                        myLocationListener);
            } catch(SecurityException e) {
                Log.d("g53mdp", e.toString());
            }
        }

        public void stopRunning() {

            if (mLocationManager != null) {
                mLocationManager.removeUpdates(myLocationListener);
            }

        }
    }

    private class MyLocationListener implements LocationListener {

        //Location myLocation;

        @Override
        public void onLocationChanged(Location location) {
            Log.d("g53mdp", location.getLatitude() + " " + location.getLongitude());
            Log.d("g53mdp", String.valueOf(location.distanceTo(location)));
            Intent intent = new Intent();
            intent.setAction("com.example.rahulsoni.irun.test");
            intent.putExtra("lat", location.getLatitude());
            intent.putExtra("long", location.getLongitude());
            //intent.setClass(getBaseContext(), MainActivity.class);
            //sendBroadcast(intent);
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
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.e("g53mdp", "onStartCommand");
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("g53mdp", "onBind done");
        return myBinder;
    }


}
