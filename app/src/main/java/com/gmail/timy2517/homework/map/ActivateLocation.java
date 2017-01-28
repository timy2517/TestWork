package com.gmail.timy2517.homework.map;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

/**
 * Created by Artem Novik on 22.11.2016.
 */
public class ActivateLocation implements LocationListener {

    private Location mLocation;
    private LocationManager mLocationManager;
    private LocationListener mLocationListener;

    /*protected void createLocationRequest() {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }*/

    public Location getLocation(Context context) {
        mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d("myDebug", "My location" + location.toString());
                mLocation = location;
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d("myDebug", "Error permission");
            return null;
        }
        Log.d("myDebug", "not error permission");
        Log.d("myDebug", "gps " + mLocationManager.isProviderEnabled(mLocationManager.GPS_PROVIDER));
        Log.d("myDebug", "gps " + mLocationManager.isProviderEnabled(mLocationManager.NETWORK_PROVIDER));
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, mLocationListener);
        mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, mLocationListener);
        //Log.d("myDebug", "My location" + mLocationManager.getLastKnownLocation(mLocationManager.GPS_PROVIDER).toString());


        return mLocation;
    }



    @Override
    public void onLocationChanged(Location location) {
        Log.d("myDebug", "My location" + location.toString());
        mLocation = location;

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
