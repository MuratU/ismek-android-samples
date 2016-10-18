package com.dnkilic.application20;

public class Circle implements Calculator {

    int mRadius;

    public Circle(int mRadius) {
        this.mRadius = mRadius;
    }

    public int getmRadius() {
        return mRadius;
    }

    public void setmRadius(int mRadius) {
        this.mRadius = mRadius;
    }


    @Override
    public double calculateAreax() {
        return Math.PI * Math.pow(mRadius, 2);
    }

    @Override
    public double calculateSideLenghtx() {
        return 2 * Math.PI * mRadius;
    }
}
