package com.dnkilic.example10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

/* Java Classes and Methods */

public class CircleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Circle circle1 = new Circle(2);
        Log.i("1.Dairenin alanı", circle1.alanHesapla() + "");
        Log.i("1.Dairenin çevresi", circle1.cevreHesapla() + "");

        Circle circle2 = new Circle(4);
        Log.i("2.Dairenin alanı", circle2.alanHesapla() + "");
        Log.i("2.Dairenin çevresi", circle2.cevreHesapla() + "");

        Circle circle3 = new Circle(8);
        Log.i("3.Dairenin alanı", circle3.alanHesapla() + "");
        Log.i("3.Dairenin çevresi", circle3.cevreHesapla() + "");

        Circle circle4 = new Circle(16);
        Log.i("4.Dairenin alanı", circle4.alanHesapla() + "");
        Log.i("4.Dairenin çevresi", circle4.cevreHesapla() + "");

        Circle circle5 = new Circle(32);
        Log.i("5.Dairenin alanı", circle5.alanHesapla() + "");
        Log.i("5.Dairenin çevresi", circle5.cevreHesapla() + "");
    }
}
