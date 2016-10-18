package com.dnkilic.androiddatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class InsertionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertion);
        
        String dbName = getIntent().getStringExtra("DatabaseName");



    }
}
