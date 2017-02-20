package com.dnkilic.alarmy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clocky(View view)
    {
        Button selected = (Button) view;

        EditText etAlarm = (EditText) findViewById(R.id.etAlarm);
        EditText etCountdown = (EditText) findViewById(R.id.etCountdown);

        AlarmSetter alarmSetter = new AlarmSetter(this);

        switch (selected.getId())
        {
            case R.id.btnAlarm:
                alarmSetter.setAlarmClock(etAlarm.getText().toString());
                break;
            case R.id.btnCountdown:
                alarmSetter.setCountdownClock(etCountdown.getText().toString());
                break;
        }
    }
}
