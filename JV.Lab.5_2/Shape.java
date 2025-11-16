package org.example;
import java.io.Serializable;
import java.util.Comparator;

public abstract class Shape implements Drawable, Serializable {
    private static final long serialVersionUID = 1L;
    protected String shapeColor;
    public Shape(String shapeColor) {
        this.shapeColor = shapeColor;
    }

    public abstract double calcArea();

    public void draw() {
        System.out.println(this.toString());
    }

    public String toString() {
        return "color=" + shapeColor + "; area=" + String.format("%.2f", calcArea());
    }

    // Компарaтори
    public static Comparator<Shape> AreaComparator = new Comparator<Shape>() {
        @Override
        public int compare(Shape s1, Shape s2) {
            // Порівняння за зростанням площі
            return Double.compare(s1.calcArea(), s2.calcArea());
        }
    };

    public static Comparator<Shape> ColorComparator = new Comparator<Shape>() {
        @Override
        public int compare(Shape s1, Shape s2) {
            // Порівняння кольорів за алфавітним порядком
            return s1.shapeColor.compareTo(s2.shapeColor);
        }
    };
}