package org.example;
import java.io.Serializable;

public class Circle extends Shape implements Serializable {
    private static final long serialVersionUID = 1L;
    private double radius;

    public Circle(String shapeColor, double radius) {
        super(shapeColor);
        this.radius = radius;
    }

    @Override
    public double calcArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public String toString() {
        return "Circle: " + super.toString() + "; radius=" + String.format("%.2f", radius);
    }
}