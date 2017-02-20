package com.dnkilic.zci;

public interface WebResultListener {
    void onSuccess(Place element);
    void onFail(String message);
}
