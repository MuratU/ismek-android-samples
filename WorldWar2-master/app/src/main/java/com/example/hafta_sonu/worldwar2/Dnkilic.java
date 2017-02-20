package com.example.hafta_sonu.worldwar2;

import java.util.InputMismatchException;

/**
 * Created by Hafta_Sonu on 20.11.2016.
 */

public class Dnkilic {
    char cVal;
    int iVal;

    public Dnkilic(String value) {
        if(value.length() == 2)
        {
            String charValue = value.substring(0,1);
            String intValue = value.substring(1,2);

            if(Character.isLetter(charValue.charAt(0)) && Character.isDigit(intValue.charAt(0)))
            {
                cVal = charValue.charAt(0);
                iVal = Integer.parseInt(intValue);
            }
            else
            {
                cVal = 'z';
                iVal = 9;
            }
        }
        else
        {
            cVal = 'z';
            iVal = 9;
        }
    }
}
