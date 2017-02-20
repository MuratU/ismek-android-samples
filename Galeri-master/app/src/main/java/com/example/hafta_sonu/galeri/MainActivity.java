package com.example.hafta_sonu.galeri;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Rectangle rectangle = new Rectangle(10, 10);
        rectangle.getArea();
        rectangle.whoAmI();
    }
}
