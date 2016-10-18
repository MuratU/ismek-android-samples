package com.dnkilic.location;

import android.app.Activity;
import android.location.Address;

public class LocationManager implements LocationUpdateListener{

    private Activity mContext;
    private LocationUpdateListener mListener;

    public LocationManager(Activity context) {
        mContext = context;
        mListener = this;
    }

    public String getAddress()
    {
        LocationService ls = new LocationService(mContext, mListener);
        ls.connectLocationService();

        return null;
    }

    @Override
    public void onLocationUpdateError(int error) {
        String result = null;
        switch (error)
        {
            case LocationService.ERROR:
                result = "Konum alınırken bir hata oluştu.";
                break;
            case LocationService.LOCATION_SERVICE_NOT_AVAILABLE:
                result = "Konum servisi kapalı.";
                break;
            default:
                result = "Konum servisi ile ilgili bilmediğim bir hata oluştu.";
                break;
        }
    }

    @Override
    public void onLocationUpdate(Address address) {

        String fullAddress = "";
        for(int i=0 ;  i<=address.getMaxAddressLineIndex(); i++)
        {
            fullAddress = fullAddress + " " + address.getAddressLine(i);
        }

        String cityName = address.getAdminArea();
        double latitude = address.getLatitude();
        double longtitude = address.getLongitude();
    }
}
