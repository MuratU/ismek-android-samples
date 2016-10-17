package com.dnkilic.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int variable = 19;

        Log.i("deneme","girdim");

        switch (variable)
        {
            case 1:
                Log.i("deneme","1");
            case 2:
                Log.i("deneme","2");
            case 3:
                Log.i("deneme","3");
            default:
        }

        Log.i("deneme","çıktım");
    }
}
