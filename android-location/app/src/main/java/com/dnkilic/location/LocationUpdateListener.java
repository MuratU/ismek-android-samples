package com.dnkilic.location;

import android.location.Address;

public interface LocationUpdateListener {
    void onLocationUpdateError(int error);
    void onLocationUpdate(Address address);
}
