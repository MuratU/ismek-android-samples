package com.dnkilic.app9;

import android.util.Log;

public class Bird {

    String mType;
    String mColor;

    public Bird(String type)
    {
        mColor = "Black";
        mType = type;
    }

    public String getColor()
    {
        return mColor;
    }

    public void setColor(String color)
    {
        mColor = color;
    }

    public String getType()
    {
        return mType;
    }

    public void fly()
    {
        Log.i("KuşUçtu", mType);
    }
}
