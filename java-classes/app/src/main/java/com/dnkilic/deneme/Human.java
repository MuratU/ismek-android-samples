package com.dnkilic.deneme;

public class Human {

    boolean mConscience;
    boolean mSensitivity;
    boolean mIsWomen;
    String mEyesColor;
    double mHeight;
    double mWeight;

    public Human(boolean isWomen, String eyesColor) {
        mIsWomen = isWomen;
        mEyesColor = eyesColor;
    }

    public void setConscience(boolean conscience) {
        mConscience = conscience;
    }

    public void setSensitivity(boolean sensitivity) {
        mSensitivity = sensitivity;
    }

    public void setHeight(double height) {
        mHeight = height;
    }

    public void setWeight(double weight) {
        mWeight = weight;
    }
}
