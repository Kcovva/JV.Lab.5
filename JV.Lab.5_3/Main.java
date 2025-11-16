package org.example;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in, "UTF-8");

        while (true) {
            System.out.println("\n(˶ᵔ ᵕ ᵔ˶) Меню:");
            System.out.println("1.Зашифрувати і зберегти в файл");
            System.out.println("2.Зчитати з файлу і дешифрувати");
            System.out.println("0.Вихід");
            System.out.print("Вибір: ");

            String choice = scanner.nextLine().trim();
            try {
                if ("0".equals(choice)) {
                    System.out.println("Вихід.");
                    break;
                } else if ("1".equals(choice)) {
                    handleEncryptAndSave(scanner);
                } else if ("2".equals(choice)) {
                    handleLoadAndDecrypt(scanner);
                } else {
                    System.out.println("Невірний вибір, спробуйте ще раз.");
                }
            } catch (InputMismatchException ime) {
                System.out.println("Помилка введення: " + ime.getMessage());
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Сталася помилка: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void handleEncryptAndSave(Scanner scanner) {
        System.out.println("\nВведіть текст для шифрування (натисніть Enter для завершення):");
        String plain = scanner.nextLine();

        System.out.println("Введіть ключ:");
        String key = scanner.nextLine();
        if (key.isEmpty()) {
            System.out.println("Ключ не може бути порожнім. Завершення.");
            return;
        }

        String cipher = CrypUtils.encrypt(plain, key);
        System.out.println("Текст зашифровано.");

        System.out.println("Вкажіть шлях та ім'я файлу для збереження:");
        String path = scanner.nextLine().trim();
        if (path.isEmpty()) {
            System.out.println("Шлях не може бути порожнім. Завершення.");
            return;
        }

        EncrData data = new EncrData(cipher, "Зашифровано ключем введеним користувачем");

        try {
            FileManager.saveToFile(data, path);
            System.out.println("Файл успішно збережено: " + path);
        } catch (IOException ioe) {
            System.out.println("Не вдалося зберегти файл: " + ioe.getMessage());
        }
    }

    private static void handleLoadAndDecrypt(Scanner scanner) {
        System.out.println("\nВкажіть шлях до файлу з зашифрованим вмістом:");
        String path = scanner.nextLine().trim();
        if (path.isEmpty()) {
            System.out.println("Шлях не може бути порожнім. Завершення.");
            return;
        }

        try {
            EncrData data = FileManager.loadFromFile(path);
            System.out.println("Файл зчитано. (Примітка: " + data.getNote() + ")");

            System.out.println("Введіть ключ для дешифрування:");
            String key = scanner.nextLine();
            if (key.isEmpty()) {
                System.out.println("Ключ не може бути порожнім. Завершення.");
                return;
            }

            String plain = CrypUtils.decrypt(data.getCipherText(), key);
            System.out.println("\n ツ Розшифрований текст ");
            System.out.println(plain);
            System.out.println("---------------------------");

        } catch (IOException ioe) {
            System.out.println("IO помилка під час зчитування файлу: " + ioe.getMessage());
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Неправильний формат файлу: " + cnfe.getMessage());
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}
