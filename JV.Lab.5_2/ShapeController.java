package org.example;
import java.util.Arrays;
import java.util.Random;

public class ShapeController {
    private Shape[] shapes;
    private ShapeView view;

    public ShapeController(ShapeView view) {
        this.view = view;
        this.shapes = createShapeArray(12);
    }

    private Shape[] createShapeArray(int size) {
        Shape[] array = new Shape[size];
        Random rand = new Random();
        String[] colors = {"Red", "Green", "Blue", "Yellow", "Black", "White"};
        for (int i = 0; i < size; i++) {

            String color = colors[rand.nextInt(colors.length)];

            double val1 = 1 + rand.nextDouble() * 9; // від 1 до 10
            double val2 = 1 + rand.nextDouble() * 9;

            // Випадковий вибір фігури
            switch (rand.nextInt(3)) {
                case 0: // Rectangle
                    array[i] = new Rectangle(color, val1, val2);
                    break;
                case 1: // Triangle
                    double sideA, sideB, sideC;
                    // Повторюємо, доки не знайдемо валідний трикутник
                    do {
                        sideA = 1 + rand.nextDouble() * 9;
                        sideB = 1 + rand.nextDouble() * 9;
                        sideC = 1 + rand.nextDouble() * 9;
                    } while (sideA + sideB <= sideC || sideA + sideC <= sideB || sideB + sideC <= sideA);

                    array[i] = new Triangle(color, sideA, sideB, sideC);
                    break;
                case 2: // Circle
                    array[i] = new Circle(color, val1);
                    break;
            }
        }
        return array;
    }

    // Методи обробки масиву
    public void displayAllShapes() {
        view.displayArray("Початковий набір даних", shapes);
    }

    public double calculateAndDisplayTotalArea() {
        double totalArea = 0;
        for (Shape shape : shapes) {
            totalArea += shape.calcArea();
        }
        view.displayTotalArea(totalArea);
        return totalArea;
    }

    public double calculateAndDisplayAreaByType(Class<? extends Shape> shapeClass, String typeName) {
        double totalArea = 0;
        for (Shape shape : shapes) {
            if (shapeClass.isInstance(shape)) {
                totalArea += shape.calcArea();
            }
        }
        view.displayTotalAreaByType(typeName, totalArea);
        return totalArea;
    }

    public void sortAndDisplayByArea() {
        Shape[] sortedShapes = Arrays.copyOf(shapes, shapes.length);
        Arrays.sort(sortedShapes, Shape.AreaComparator);
        view.displayArray("Набір даних, впорядкований за збільшенням площі", sortedShapes);
    }

    public void sortAndDisplayByColor() {
        Shape[] sortedShapes = Arrays.copyOf(shapes, shapes.length);
        Arrays.sort(sortedShapes, Shape.ColorComparator);
        view.displayArray("Набір даних, впорядкований за кольором", sortedShapes);
    }

    public void setShapes(Shape[] newShapes) {
        this.shapes = newShapes;
    }

    public Shape[] getShapes() {
        return shapes;
    }

}
