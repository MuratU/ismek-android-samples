package com.dnkilic.day2.split;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dnkilic.day2.R;

public class MainActivity extends AppCompatActivity {

    TextView mTvQueryResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvQueryResult = (TextView)findViewById(R.id.tvQueryResults);
    }

    public void list(View jdakjsadkbjjbkhdsaghsakjbkjvakbj)
    {
        String ogrenci1 = "Kayseri/Çakırbey/Erkek/Ahmet";
        String ogrenci2 = "Vancouer Kırman Erkek Kahraman";
        String ogrenci3 = "KırklarelixxxUsluxxxKadınxxxZeynepG.";
        String ogrenci4 = "Nikah-Akbaba-Erkek-Onur.";
        String patron = "Ağrı Kılıç Erkek Doğan";

        String ogr1 [] = ogrenci1.split("/");
        String ogr2 [] = ogrenci2.split(" ");
        String ogr3 [] = ogrenci3.split("xxx");
        String ogr4 [] = ogrenci4.split("-");
        String [] insan = patron.split(" ");

        // memleket - soyisim - cinsiyet - isim
        // isim - soyisim - memleket - cinsiyet

        String veri = ogr1[3] + " " + ogr1[1] + " " + ogr1[0] + " " + ogr1[2] + " \n" +
                ogr2[3] + " " + ogr2[1] + " " + ogr2[0] + " " + ogr2[2] + " \n" +
                ogr3[3] + " " + ogr3[1] + " " + ogr3[0] + " " + ogr3[2] + " \n" +
                ogr4[3] + " " + ogr4[1] + " " + ogr4[0] + " " + ogr4[2] + " \n" +

                insan[3] + " " + insan[1] + " " + insan[0] + " " + insan[2];

        mTvQueryResult.setText(veri);
    }
}
