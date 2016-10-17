package com.dnkilic.example10;

public class Circle {

    double mRadius;

    public Circle(double radius) {
        mRadius = radius;
    }

    public double alanHesapla()
    {
        double result = Math.PI * Math.pow(mRadius, 2);
        double result2 = Math.PI * mRadius * mRadius;

        return result;
    }

    public double cevreHesapla()
    {
        // pi * 2 * r
        double result = 2 * Math.PI * mRadius;
        return result;
    }
}
