package com.dnkilic.application18;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dnkilic.application18.data.Employee;
import com.dnkilic.application18.data.Salary;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Employee isci1 = new Employee("Doğan Kılıç", 900, "Sarılgöl");
        Employee isci2 = new Employee("Bahar Yaman", 901, "Karagümrük");


        Salary sendikali1 = new Salary("Barış Algül", 902, "Balat", 1000);
        sendikali1.mailCheck();

        System.out.println("**********************************************");

        Employee sendikali2 = new Salary("Ece Kaçmaz", 903, "Çarşamba", 2000);
        sendikali2.mailCheck();
    }
}
