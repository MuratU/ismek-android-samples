package com.dnkilic.application20;

/**
 * Created by dnkilic on 27/03/16.
 */
public class Rectangle implements Calculator {

    int mLenght;
    int mHeight;

    public Rectangle(int mLenght, int mHeight) {
        this.mLenght = mLenght;
        this.mHeight = mHeight;
    }

    public int getmLenght() {
        return mLenght;
    }

    public void setmLenght(int mLenght) {
        this.mLenght = mLenght;
    }

    public int getmHeight() {
        return mHeight;
    }

    public void setmHeight(int mHeight) {
        this.mHeight = mHeight;
    }

    @Override
    public double calculateAreax() {
        return mHeight * mLenght;
    }

    @Override
    public double calculateSideLenghtx() {
        return 2 * (mLenght + mHeight);
    }
}
