package com.dnkilic.applicatin32;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FruitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);

        getSupportActionBar().setTitle(getIntent().getStringExtra("Fruit"));

        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setText(getIntent().getStringExtra("Fruit"));

    }
}
