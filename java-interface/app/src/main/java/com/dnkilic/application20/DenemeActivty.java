package com.dnkilic.application20;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DenemeActivty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deneme_activty);
    }

    public void tikla(View dsjkbsdjkadshbkkbhs)
    {
        Button btn = (Button) dsjkbsdjkadshbkkbhs;
        btn.setTextColor(getResources().getColor(R.color.colorAccent));
        //bıdı bıdı
    }
}
