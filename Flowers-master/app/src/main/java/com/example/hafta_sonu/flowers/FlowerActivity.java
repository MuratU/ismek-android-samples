package com.example.hafta_sonu.flowers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class FlowerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flower);

        Intent intent = getIntent();
        String name = intent.getStringExtra("NAME");
        int location = intent.getIntExtra("IMG_LOCATION", 0);
        String description = intent.getStringExtra("DESCRIPTION");
        String url = intent.getStringExtra("URL");

        ImageView ivFlower = (ImageView) findViewById(R.id.ivFlower);
        ivFlower.setBackgroundResource(location);

        TextView tvDescription = (TextView) findViewById(R.id.tvDescription);
        tvDescription.setText(description);

        getSupportActionBar().setTitle(name);
    }
}
