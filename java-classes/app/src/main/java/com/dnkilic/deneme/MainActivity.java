package com.dnkilic.deneme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void create(View v)
    {
        Human dnkilic = new Human(false, "Bordo");
        dnkilic.mWeight = 200;
        dnkilic.setWeight(150);

        dnkilic.mEyesColor = "Mavi";

        dnkilic.setConscience(true);
        dnkilic.setSensitivity(false);
    }
}
