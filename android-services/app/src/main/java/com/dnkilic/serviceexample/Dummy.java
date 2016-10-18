package com.dnkilic.serviceexample;

import android.app.NotificationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Dummy extends AppCompatActivity {

    private static final String NOTIFICATION_ID = "NOTIFICATION_ID";
    private static final String CALL_TOKEN = "CALL_TOKEN";
    private static final String SET_CALL_TO_REJECTED_TAG = "SET_CALL_TO_REJECTED";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setCallToRejected(getIntent().getStringExtra(CALL_TOKEN));

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(getIntent().getIntExtra(NOTIFICATION_ID, -1));
        finish();
    }

    private void setCallToRejected(String callToken) {

        String URL = "http://192.168.10.42:3031/api/services/mia/mobile/setCallToRejected";

        Map<String, String> params = new HashMap<>();

        params.put("CallToken", callToken);

        Log.d("com.sestek.mca", "Request setCallToRejected : " + "callToken :" + callToken);

        JsonObjectRequest setCallToRejectedRequest = new JsonObjectRequest(Request.Method.POST, URL,

                new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONObject json = new JSONObject(response.toString());

                            boolean isSuccess = json.getBoolean("success");
                            boolean isUnAuthorizedRequest = json.getBoolean("unAuthorizedRequest");
                            String globalError = json.getString("error");
                            JSONObject result = json.getJSONObject("result");
                            String localError = result.getString("error");

                            Log.d("com.sestek.mca", "NetworkResponse setCallToRejected: " + "success :" + isSuccess + "\n" +
                                    "globalError :" + globalError + "\n" +
                                    "localError :" + localError + "\n" +
                                    "isAuth :" + isUnAuthorizedRequest);


                            if (isSuccess) {
                                if (localError.equals("null")) {
                                    System.out.println("başarılı");
                                } else {
                                    System.out.println("local error var");
                                }
                            } else {
                                System.out.println("global error");
                            }
                        } catch (JSONException e) {
                            System.out.println("exception");
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("response error");
                        error.printStackTrace();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("User-agent", System.getProperty("http.agent"));

                return headers;
            }
        };

        setCallToRejectedRequest.setTag(SET_CALL_TO_REJECTED_TAG);
        setCallToRejectedRequest.setRetryPolicy(new DefaultRetryPolicy(
                60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppController.getInstance().addToRequestQueue(setCallToRejectedRequest);
    }
}
