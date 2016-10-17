package com.dnkilic.application15;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Alive interestingAlive = new Alive();
        //interestingAlive.whoIsYourAncestor();



    }

    public void changeTextColor(View dknsbndsbnnbksdv)
    {
        MyTextView tv1 = (MyTextView) findViewById(R.id.tvMyTv);
        tv1.setTextColor(getResources().getColor(R.color.colorAccent));
    }

}
