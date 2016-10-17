package com.dnkilic.app13;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    /* Classes and Constructors */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Dnkilic param1 = new Dnkilic("a8");

        Dnkilic param2 = new Dnkilic();

        Dnkilic param3 = new Dnkilic("ff");
    }
}
