package com.dnkilic.serviceexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void stopService(View v)
    {
        stopService(new Intent(getBaseContext(), CallObservationService.class));
    }

    public void startService(View v)
    {
        startService(new Intent(getBaseContext(), CallObservationService.class));
    }
}
