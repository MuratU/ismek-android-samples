package com.example.hafta_sonu.galeri;

public class Rectangle extends Shape {

    double width;
    double lenght;

    public Rectangle(double width, double lenght) {
        this.width = width;
        this.lenght = lenght;
    }

    @Override
    double getArea() {
        //TODO a * b
        double area = width * lenght;
        return area;
    }
}
