package com.dnkilic.application4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvResult;
    private EditText etInputNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResult = (TextView) findViewById(R.id.tvTopla);
        etInputNumber = (EditText) findViewById(R.id.etTopla);
    }

    public void topla(View v){
        int value = Integer.valueOf(etInputNumber.getText().toString());

        int result = 0;

        for (int i = 0; i<=value;i++){

            if(i%10 == 0)
            {
                continue;
            }

            if(i == 667)
            {
                result = 0;
                break;
            }

            result += i;
        }

        tvResult.setText(""+result);
    }
}
