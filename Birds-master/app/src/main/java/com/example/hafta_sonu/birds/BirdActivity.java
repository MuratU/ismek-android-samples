package com.example.hafta_sonu.birds;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class BirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bird);

        Intent i = getIntent();
        String birdType = i.getStringExtra("KUS_TURU").toLowerCase();
        String backgroundColor = i.getStringExtra("ARKAPLAN_RENGİ");

        RelativeLayout rl = (RelativeLayout)findViewById(R.id.activity_bird);
        switch (backgroundColor)
        {
            case "mavi":
                rl.setBackgroundResource(android.R.color.holo_blue_dark);
                break;
            case "kırmızı":
                rl.setBackgroundResource(android.R.color.holo_red_dark);
                break;
            case "yeşil":
                rl.setBackgroundResource(android.R.color.holo_green_dark);
                break;
            case "mor":
                rl.setBackgroundResource(android.R.color.holo_purple);
                break;
            case "turuncu":
                rl.setBackgroundResource(android.R.color.holo_orange_light);
                break;
            case "sarı":
                rl.setBackgroundResource(R.color.yellow);
                break;
            case "gri":
                rl.setBackgroundResource(R.color.grey);
                break;
            case "siyah":
                rl.setBackgroundResource(android.R.color.black);
                break;
        }

        ImageView ivBird = (ImageView) findViewById(R.id.ivBird);

        switch (birdType)
        {
            case "güvercin":
                ivBird.setBackgroundResource(R.drawable.guvercin);
                break;
            case "serçe":
                ivBird.setBackgroundResource(R.drawable.serce);
                break;
            case "bülbül":
                ivBird.setBackgroundResource(R.drawable.bulbul);
                break;
            case "sığırcık":
                ivBird.setBackgroundResource(R.drawable.sigircik);
                break;
            case "saka":
                ivBird.setBackgroundResource(R.drawable.saka);
                break;
        }

        getSupportActionBar().setTitle(birdType);
        //getActionBar().setTitle("Güvercin");

    }
}
