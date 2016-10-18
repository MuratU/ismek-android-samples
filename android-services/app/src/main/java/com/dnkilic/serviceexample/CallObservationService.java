package com.dnkilic.serviceexample;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CallObservationService extends Service {

    private NotificationCompat.Builder mNotificationBuilder;
    private ScheduledExecutorService mScheduler;
    private static final String GET_NEW_CALLS_FOR_MOBILE_TAG = "GET_NEW_CALLS_FOR_MOBILE";
    private static final String SET_CALL_TO_CONNECTED_TAG = "SET_CALL_TO_CONNECTED";
    private static final String SET_CALL_TO_ACCEPTED_TAG = "SET_CALL_TO_ACCEPTED";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();

        mScheduler =
                Executors.newSingleThreadScheduledExecutor();

        mScheduler.scheduleAtFixedRate
                (new Runnable() {
                    public void run() {
                        System.out.println("Servis çalışıyor");
                        getNewCallsForMobile("qwe123@g.com", "123456");
                       // startNotification("dskklnds");
                    }
                }, 1, 5, TimeUnit.SECONDS);

        return START_REDELIVER_INTENT;
    }

    private void startNotification(String caller, String callToken)
    {
        setCallToConnected(callToken);

        int notificationId = new Random().nextInt();
        Intent intent = new Intent(getBaseContext(), Dummy.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("NOTIFICATION_ID", notificationId);
        intent.putExtra("CALL_TOKEN", callToken);
        PendingIntent dismissIntent = PendingIntent.getActivity(getBaseContext(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        //Yanıtla butonu ile ise communitacion activity açılacak

        mNotificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.icon)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.icon))
                .setColor(getResources().getColor(R.color.sestekOrange))
                .setContentTitle(caller)
                .setWhen(System.currentTimeMillis())
                .setContentText("Gelen çağrı")
                .addAction(R.drawable.ic_reject, "Kapat", dismissIntent)
                .addAction(R.drawable.ic_accept, "Yanıtla", null)
        .setPriority(Notification.PRIORITY_MAX);

        if (Build.VERSION.SDK_INT >= 21) mNotificationBuilder.setVibrate(new long[0]);

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(notificationId, mNotificationBuilder.build());
    }

    private void getNewCallsForMobile(String userName, String password) {

        String URL = "http://192.168.10.42:3031/api/services/mia/mobile/getNewCallsForMobile";

        Map<String, String> params = new HashMap<>();

        params.put("username", userName);
        params.put("password", password);

        Log.d("com.sestek.mca", "Request getNewCallsForMobile : " + "username :" + userName + "password :" + password);

        JsonObjectRequest getNewCallsForMobileRequest = new JsonObjectRequest(Request.Method.POST, URL,

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
                            JSONArray callList = result.getJSONArray("callList");

                            Log.d("com.sestek.mca", "NetworkResponse getNewCallsForMobile: " + "success :" + isSuccess + "\n" +
                                    "globalError :" + globalError + "\n" +
                                    "localError :" + localError + "\n" +
                                    "callList :" + callList + "\n" +
                                    "isAuth :" + isUnAuthorizedRequest);


                            if (isSuccess) {
                                if (localError.equals("null")) {
                                    System.out.println("başarılı : " + callList);

                                    if (callList != null ) {

                                        if(callList.length() > 0)
                                        {
                                            JSONObject call = callList.getJSONObject(0);
                                            startNotification(call.getString("callerPhoneNumber"), call.getString("callToken"));

                                            /*[{"callToken":"609eae4a5f6e09b5d1d103f305a3b0f01b689a5041f0ac79ee2949e76abc24feac85f77be02add3d13c0fdcfd387afdb",
                                                "calledEndUserName":"aras",
                                                "calledEndUserSurname":"dagly",
                                                "calledEndUserGender":0,
                                                "calledPhoneNumber":"0999123456",
                                                "callerEndUserName":"Ivr",
                                                "callerEndUserSurname":"User",
                                                "callerEndUserGender":0,
                                                "callerPhoneNumber":"67575424",
                                                "firstMessage":"",
                                                "creationTime":"2016-03-25T09:58:24.843",
                                                "error":null}]*/

                                            System.out.println("arama var");
                                        }
                                        else
                                        {
                                            System.out.println("arama yok");
                                        }
                                    }

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

        getNewCallsForMobileRequest.setTag(GET_NEW_CALLS_FOR_MOBILE_TAG);
        getNewCallsForMobileRequest.setRetryPolicy(new DefaultRetryPolicy(
                60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppController.getInstance().addToRequestQueue(getNewCallsForMobileRequest);
    }

    private void setCallToConnected(String callToken) {

        String URL = "http://192.168.10.42:3031/api/services/mia/mobile/setCallToConnected";

        Map<String, String> params = new HashMap<>();

        params.put("CallToken", callToken);

        Log.d("com.sestek.mca", "Request setCallToConnected : " + "callToken :" + callToken);

        JsonObjectRequest setCallToConnectedRequest = new JsonObjectRequest(Request.Method.POST, URL,

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

                            Log.d("com.sestek.mca", "NetworkResponse setCallToConnected: " + "success :" + isSuccess + "\n" +
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

        setCallToConnectedRequest.setTag(SET_CALL_TO_CONNECTED_TAG);
        setCallToConnectedRequest.setRetryPolicy(new DefaultRetryPolicy(
                60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppController.getInstance().addToRequestQueue(setCallToConnectedRequest);
    }

    private void setCallToAccepted(String callToken) {

        String URL = "http://192.168.10.42:3031/api/services/mia/mobile/setCallToAccepted";

        Map<String, String> params = new HashMap<>();

        params.put("CallToken", callToken);

        Log.d("com.sestek.mca", "Request setCallToAccepted : " + "callToken :" + callToken);

        JsonObjectRequest setCallToAcceptedRequest = new JsonObjectRequest(Request.Method.POST, URL,

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

                            Log.d("com.sestek.mca", "NetworkResponse setCallToAcceptedRequest: " + "success :" + isSuccess + "\n" +
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

        setCallToAcceptedRequest.setTag(SET_CALL_TO_ACCEPTED_TAG);
        setCallToAcceptedRequest.setRetryPolicy(new DefaultRetryPolicy(
                60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppController.getInstance().addToRequestQueue(setCallToAcceptedRequest);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        mScheduler.shutdown();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }
}
