package com.dnkilic.deneme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(getApplication(),"Ben onCreatedeyim", Toast.LENGTH_SHORT ).show();
        Log.i("ActivityLifecycle", "onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplication(),"Ben onResume", Toast.LENGTH_SHORT ).show();
        Log.i("ActivityLifecycle", "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(getApplication(),"Ben onRestart", Toast.LENGTH_SHORT ).show();
        Log.i("ActivityLifecycle", "onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplication(),"Ben onPause", Toast.LENGTH_SHORT ).show();
        Log.i("ActivityLifecycle", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplication(),"Ben onStop", Toast.LENGTH_SHORT ).show();
        Log.i("ActivityLifecycle", "onStop");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplication(),"Ben onStart", Toast.LENGTH_SHORT ).show();
        Log.i("ActivityLifecycle", "onStart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplication(),"Ben onDestroy", Toast.LENGTH_SHORT ).show();
        Log.i("ActivityLifecycle", "onDestroy");
    }
}
