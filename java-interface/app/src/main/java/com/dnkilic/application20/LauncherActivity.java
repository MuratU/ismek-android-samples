package com.dnkilic.application20;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Circle c1 = new Circle(2);
        System.out.println("Yarıçapı 2 olan dairenin alanı : " + c1.calculateAreax());
        System.out.println("Yarıçapı 2 olan dairenin çevresi : " + c1.calculateSideLenghtx());


        Rectangle r1 = new Rectangle(1,2);
        System.out.println("Kenarları 1 ve 2 olan diktörtgenin alanı : " + r1.calculateAreax());
        System.out.println("Kenarları 1 ve 2 olan diktörtgenin çevresi : " + r1.calculateSideLenghtx());

        Square s1 = new Square(2);
        System.out.println("Kenarı 2 olan karenin alanı : " + s1.calculateAreax());
        System.out.println("Kenarı 2 olan karenin çevresi : " + s1.calculateSideLenghtx());


    }
}
