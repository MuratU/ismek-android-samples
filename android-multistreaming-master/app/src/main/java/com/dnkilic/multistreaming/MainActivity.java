package com.dnkilic.multistreaming;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickServer(View view)
    {
        Intent i = new Intent(MainActivity.this, ServerActivity.class);
        startActivityForResult(i, 101);
    }

    public void clickClient(View view)
    {
        Intent i = new Intent(MainActivity.this, ClientActivity.class);
        startActivityForResult(i, 102);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
