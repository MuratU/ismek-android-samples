package com.dnkilic.application2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    EditText mEtName;
    EditText mEtSurname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEtName = (EditText)findViewById(R.id.etName);
        mEtSurname = (EditText)findViewById(R.id.etSurname);
    }

    public void login(View view)
    {
        String name = mEtName.getText().toString();

        name = name.substring(0,1).toUpperCase()+
                name.substring(1,name.length()).toLowerCase();

        String surname = mEtSurname.getText().toString();

        if(name.equalsIgnoreCase("doğan") && surname.equalsIgnoreCase("kılıç"))
        {
            Toast.makeText(getApplicationContext(), "Hoşgeldin patron!", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Biz sizi sonra ararız.", Toast.LENGTH_LONG).show();
        }
    }
}
