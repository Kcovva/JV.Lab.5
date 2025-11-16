package org.example;

public class ShapeView {

    public void displayArray(String title, Shape[] shapes) {
        System.out.println("\n.;.:. " + title);
        if (shapes == null || shapes.length == 0) {
            System.out.println("Масив порожній.");
            return;
        }
        for (Shape shape : shapes) {
            shape.draw();
        }
    }

    public void displayTotalArea(double totalArea) {
        System.out.println("\nСумарна площа всіх фігур: " + String.format("%.2f", totalArea));
    }

    public void displayTotalAreaByType(String type, double totalArea) {
        System.out.println("Сумарна площа фігур типу " + type + ": " + String.format("%.2f", totalArea));
    }
}
