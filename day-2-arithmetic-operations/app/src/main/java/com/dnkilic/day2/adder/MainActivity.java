package com.dnkilic.day2.adder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void add(View view)
    {
        EditText etNumber1 = (EditText) findViewById(R.id.etNumber1);
        EditText etNumber2 = (EditText) findViewById(R.id.etNumber2);

        String strNumber1 = etNumber1.getText().toString();
        String strNumber2 = etNumber2.getText().toString();

        //Şimdi String değerleri int'e çevirmemiz gerekiyor

        int number1 = Integer.valueOf(strNumber1);
        int number2 = Integer.valueOf(strNumber2);

        int intResult = number1 + number2;


        //Şimdi int değeri String'e çevirmeliyiz.
        String result = "" + intResult;

        TextView tvResult = (TextView) findViewById(R.id.tvResult);
        tvResult.setText(result);
    }
}
