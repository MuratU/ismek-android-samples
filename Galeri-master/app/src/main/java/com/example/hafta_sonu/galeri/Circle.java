package com.example.hafta_sonu.galeri;

public class Circle extends Shape {
    double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    double getArea() {
        double area = Math.PI * radius * radius;
        return area;
    }
}
