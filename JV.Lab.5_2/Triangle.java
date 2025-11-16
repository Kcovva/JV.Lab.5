package org.example;
import java.io.Serializable;

public class Triangle extends Shape implements Serializable {
    private static final long serialVersionUID = 1L;
    private double sideA;
    private double sideB;
    private double sideC;

    public Triangle(String shapeColor, double sideA, double sideB, double sideC) {
        super(shapeColor);
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    @Override
    public double calcArea() {
        double s = (sideA + sideB + sideC) / 2; // Півпериметр
        return Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC)); // Формула Герона
    }

    @Override
    public String toString() {
        return "Triangle: " + super.toString() + "; a=" + String.format("%.2f", sideA) + "; b=" + String.format("%.2f", sideB) + "; c=" + String.format("%.2f", sideC);
    }
}