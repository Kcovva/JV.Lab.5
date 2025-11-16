package org.example;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ShapeView view = new ShapeView();
        ShapeController controller = new ShapeController(view);

        while (true) {
            System.out.println("\n(˶ᵔ ᵕ ᵔ˶) Меню:");
            System.out.println("1. Показати всі фігури");
            System.out.println("2. Порахувати сумарну площу");
            System.out.println("3. Порахувати площу за типом");
            System.out.println("4. Сортувати за площею");
            System.out.println("5. Сортувати за кольором");
            System.out.println("6. Зберегти фігури у файл");
            System.out.println("7. Завантажити фігури з файлу");
            System.out.println("0. Вихід");
            System.out.print("Оберіть команду: ");

            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1":
                        controller.displayAllShapes();
                        break;

                    case "2":
                        controller.calculateAndDisplayTotalArea();
                        break;

                    case "3":
                        System.out.println("Введіть тип (rectangle/circle/triangle): ");
                        String type = scanner.nextLine().toLowerCase();

                        if (type.equals("rectangle"))
                            controller.calculateAndDisplayAreaByType(Rectangle.class, "прямокутник");
                        else if (type.equals("circle"))
                            controller.calculateAndDisplayAreaByType(Circle.class, "коло");
                        else if (type.equals("triangle"))
                            controller.calculateAndDisplayAreaByType(Triangle.class, "трикутник");
                        else
                            System.out.println("Невідомий тип!");
                        break;

                    case "4":
                        controller.sortAndDisplayByArea();
                        break;

                    case "5":
                        controller.sortAndDisplayByColor();
                        break;

                    case "6":
                        System.out.print("Введіть шлях і ім'я файлу для збереження: ");
                        String savePath = scanner.nextLine();

                        try {
                            FileManager.saveShapes(controller.getShapes(), savePath);
                            System.out.println("Фігури успішно збережено.");
                        } catch (Exception e) {
                            System.out.println("Помилка збереження: " + e.getMessage());
                        }
                        break;

                    case "7":
                        System.out.print("Введіть шлях до файлу: ");
                        String loadPath = scanner.nextLine();

                        try {
                            Shape[] loaded = FileManager.loadShapes(loadPath);
                            controller.setShapes(loaded);
                            System.out.println("Фігури успішно завантажені.");

                            // Вивести набір фігур після завантаження:
                            controller.displayAllShapes();

                        } catch (Exception e) {
                            System.out.println("Помилка завантаження: " + e.getMessage());
                        }
                        break;

                    case "0":
                        System.out.println("Вихід.");
                        return;

                    default:
                        System.out.println("Невідома команда.");
                }

            } catch (Exception ex) {
                System.out.println("Помилка: " + ex.getMessage());
            }
        }
    }
}
