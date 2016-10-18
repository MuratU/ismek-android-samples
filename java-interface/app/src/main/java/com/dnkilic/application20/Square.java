package com.dnkilic.application20;

/**
 * Created by dnkilic on 27/03/16.
 */
public class Square implements Calculator {

    int mLenght;

    public Square(int mLenght) {
        this.mLenght = mLenght;
    }

    public int getmLenght() {
        return mLenght;
    }

    public void setmLenght(int mLenght) {
        this.mLenght = mLenght;
    }

    @Override
    public double calculateAreax() {
        return Math.pow(mLenght, 2);
    }

    @Override
    public double calculateSideLenghtx() {
        return 4 * mLenght;
    }
}
