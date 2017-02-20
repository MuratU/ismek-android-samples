package com.dnkilic.testing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.etName) EditText etName;
    @BindView(R.id.etSurname) EditText etSurname;
    @BindView(R.id.tvConcatResult) TextView tvConcatResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);



    }

    @OnClick(R.id.btnConcat)
    public void concat(View v) {
        //String result = etName.getText().toString() + etSurname.getText().toString();
        //tvConcatResult.setText(result);
        try {
            tvConcatResult.setText(Utility.concator(etName.getText().toString() , etSurname.getText().toString()));
        } catch (Exception e) {
            tvConcatResult.setText("Girdilerinizi kontrol ediniz");
            e.printStackTrace();
        }
    }
}
