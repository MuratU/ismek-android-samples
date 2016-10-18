package com.dnkilic.application27;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lv = (ListView) findViewById(R.id.listView);


        final String [] birds =
                {"Bayağı Kar Serçesi", "Doğan", "Drongo", "Keten Kuşu", "Sarı Serçe" };

        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (this, R.layout.my_list_item, R.id.myText, birds);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(LoginActivity.this, BirdActivity.class);
                intent.putExtra("Data", birds);
                intent.putExtra("Position", position);
                intent.putExtra("WhoSelected", birds[position]);
                startActivity(intent);
            }
        });
    }
}
