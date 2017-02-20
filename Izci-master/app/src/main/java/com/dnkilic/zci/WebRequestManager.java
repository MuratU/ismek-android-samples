package com.dnkilic.zci;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Created by Hafta_Sonu on 18.02.2017.
 */

public class WebRequestManager {

    WebResultListener listener;
    Context context;

    public WebRequestManager(MainActivity activity) {
        this.listener = activity;
        this.context = activity;
    }

    public void makeRequest(String latitude, String longtitude) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url ="https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+latitude+","+longtitude+"&radius=500&type=mosque&key=AIzaSyAK-c7mRnuC_nGjW6OWWcCp9RUcxEx1p80";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        Place element = gson.fromJson (response, Place.class);
                        listener.onSuccess(element);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onFail("Yer bilgileri alınırken bir hata oluştu.");
                error.printStackTrace();
            }
        });
        queue.add(stringRequest);
    }
}
