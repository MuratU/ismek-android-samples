package com.dnkilic.location;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationService implements ConnectionCallbacks, OnConnectionFailedListener {

    private LocationUpdateListener mListener;
    private GoogleApiClient mGoogleApiClient;
    private Geocoder mGeocoder;
    private Context mContext;

    public final static int LOCATION_SERVICE_NOT_AVAILABLE = -1;
    public final static int ERROR = 0;

    public LocationService(Context context, LocationUpdateListener listener) {
        mContext = context;
        mListener = listener;
        mGeocoder = new Geocoder(context, new Locale("TR-tr"));
        buildGoogleApiClient();
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(mContext)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    public void connectLocationService() {
        mGoogleApiClient.connect();
    }

    private void updateLocation(Location location) throws IOException {
        List<Address> addresses = mGeocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
        Address address = addresses.get(0);

        if(address != null)
        {
            mListener.onLocationUpdate(address);
        }
        else
        {
            mListener.onLocationUpdateError(ERROR);
        }
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        if(LocationServices.FusedLocationApi.getLocationAvailability(mGoogleApiClient).isLocationAvailable())
        {
            Location currentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            try {
                updateLocation(currentLocation);
            } catch (Exception e) {
                e.printStackTrace();
                mListener.onLocationUpdateError(ERROR);
            }
        }
        else
        {
            mListener.onLocationUpdateError(LOCATION_SERVICE_NOT_AVAILABLE);
        }
    }

    @Override
    public void onConnectionSuspended(int cause) {
        mGoogleApiClient.connect();
        mListener.onLocationUpdateError(ERROR);
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        mListener.onLocationUpdateError(ERROR);
    }
}
