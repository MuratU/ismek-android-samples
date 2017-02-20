package com.dnkilic.testing;

/**
 * Created by Hafta_Sonu on 12.02.2017.
 */

public class Human {

    int weight = 0;

    public Human() {
        weight = 3;
    }
    public void ye (int miktar)
    {
        weight = weight + miktar;
    }

    public static void kendiniBıçakla()
    {
        System.out.println("öldüm");
    }
}
