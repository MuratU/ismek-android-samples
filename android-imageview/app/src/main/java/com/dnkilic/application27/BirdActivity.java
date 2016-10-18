package com.dnkilic.application27;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class BirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bird);

        int position = getIntent().getIntExtra("Position", -1);
        String [] birds = getIntent().getStringArrayExtra("Data");
        String selectedBird = getIntent().getStringExtra("WhoSelected");

        ImageView iv = (ImageView) findViewById(R.id.imageView);

        if(position != -1)
        {
            if(selectedBird.equals("Bayağı Kar Serçesi"))
            {
                iv.setImageResource(R.drawable.bayagiserce);
            }
            else if(selectedBird.equals("Doğan"))
            {
                iv.setImageResource(R.drawable.dogan);
            }
            else if(selectedBird.equals("Drongo"))
            {
                iv.setImageResource(R.drawable.drongo);
            }
            else if(selectedBird.equals("Keten Kuşu"))
            {
                iv.setImageResource(R.drawable.ketenkusu);
            }
            else if(selectedBird.equals("Sarı Serçe"))
            {
                iv.setImageResource(R.drawable.sariserce);
            }
        }

        //getActionBar().setTitle(selectedBird);
        getSupportActionBar().setTitle(selectedBird);

    }
}
