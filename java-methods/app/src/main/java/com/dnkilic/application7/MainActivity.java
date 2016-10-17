package com.dnkilic.application7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    int mA;
    int mB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mA = 10;
        mB = 5;
        swapAB();

        int c = 8;
        int d = 5;
        swap(c,d);

        int e = 0;
        int f = 1;
        swap(e,f);
    }

    private void swapAB() {
        int temp;
        temp = mA;
        mA = mB;
        mB = temp;
    }


    void swap (int numberone, int numbertwo)
    {
        Log.i("initial value", "numberone :" + numberone);
        Log.i("initial value", "numbertwo :" + numbertwo);

        int temp;

        temp = numberone;
        numberone = numbertwo;
        numbertwo = temp;

        Log.i("swaped value", "numberone : " + numberone);
        Log.i("swaped value" , "numbertwo : " + numbertwo);
    }
}


