package com.dnkilic.application28;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FruitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);

        TextView tv = (TextView) findViewById(R.id.textView);

        String isim = getIntent().getStringExtra("Meyve");
        tv.setText(isim);

    }
}
