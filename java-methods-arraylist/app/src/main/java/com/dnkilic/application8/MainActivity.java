package com.dnkilic.application8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> ogrenciler = new ArrayList<>();
        ogrenciler.add("Derman Bakaç");
        ogrenciler.add("Onur Akbaba");
        ogrenciler.add("Eyüp Çiçek");
        ogrenciler.add("Ahmet Yetgindağ");

        Log.i("Size of Ogrenciler","" + ogrenciler.size());

        Log.i("Size of Ogrenciler","" + getSize(ogrenciler));
    }

    int getSize(ArrayList<String> items)
    {
        int size = 0;

        for(String item : items)
        {
            size++;
        }

        return size;
    }
}





