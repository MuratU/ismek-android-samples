package com.dnkilic.app9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    /* Java Objects and Methods */

    Bird mBird1;
    Bird mBird2;
    Bird mBird3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBird1 = new Bird("Güvercin");
        mBird2 = new Bird("Martı");
        mBird3 = new Bird("Doğan");
    }

    public void flyBird1(View view)
    {
        mBird1.fly();
        mBird1.setColor("Kırmızı");
    }

    public void flyBird2(View view)
    {
        mBird2.fly();
        mBird2.setColor("Turuncu");
    }

    public void flyBird3(View view)
    {
        mBird3.fly();
        mBird3.setColor("Bordo");
    }
}
