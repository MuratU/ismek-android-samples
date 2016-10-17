package com.dnkilic.app13;

public class Dnkilic {
    
    String mDnkilic;

    public Dnkilic(String input) {
        if(input.length() == 2)
        {
            char element1 = input.charAt(0);
            char element2 = input.charAt(1);

            if(Character.isLetter(element1) && Character.isDigit(element2))
            {
                mDnkilic = input;
            }
            else
            {
                mDnkilic = "z9";
            }
        }
        else
        {
            mDnkilic = "z9";
        }
    }

    public Dnkilic()
    {
        mDnkilic = "a0";
    }
}
