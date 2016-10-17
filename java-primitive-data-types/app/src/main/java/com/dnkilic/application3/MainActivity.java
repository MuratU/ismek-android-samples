package com.dnkilic.application3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvResults, tvResultsInitialized;

    int intValue;
    boolean boolValue;
    char charValue;
    float floatValue;
    double doubleValue;
    short shortValue;
    long longValue;
    byte byteValue;

    String stringValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResults = (TextView)findViewById(R.id.tvResults);
        tvResultsInitialized = (TextView)findViewById(R.id.tvResultsInitialized);
    }

    public void test(View view)
    {
        String values = "int :" + intValue + " \n" +
                "booelan :" + boolValue + " \n" +
                "char :" + charValue + " \n" +
                "float :" + floatValue + " \n" +
                "double :" + doubleValue + " \n" +
                "short :" + shortValue + " \n" +
                "long :" + longValue + " \n" +
                "byte :" + byteValue + " \n" +
                "String :" + stringValue;

        tvResults.setText(values);
        Log.i("deneme" , values);

        intValue = 10;
        boolValue = true;
        charValue = 'a';
        floatValue = 1.2f;
        doubleValue = 1.3;
        shortValue = 10;
        longValue = 10;
        byteValue = 1;
        stringValue = "ben null değilim";

        String values2 = "int :" + intValue + " \n" +
                "booelan :" + boolValue + " \n" +
                "char :" + charValue + " \n" +
                "float :" + floatValue + " \n" +
                "double :" + doubleValue + " \n" +
                "short :" + shortValue + " \n" +
                "long :" + longValue + " \n" +
                "byte :" + byteValue + " \n" +
                "String :" + stringValue;

        tvResultsInitialized.setText(values2);
        Log.i("deneme" , values2);

    }
}
