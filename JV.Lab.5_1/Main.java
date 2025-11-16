package org.example;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in, "UTF-8");
        FileHandler fh = new FileHandler();

        try {
            //Шлях до вхідного файлу
            System.out.print("Введіть шлях до вхідного текстового файлу: ");
            String inputPathStr = scanner.nextLine().trim();
            if (inputPathStr.isEmpty()) {
                System.out.println("Порожній шлях. Завершення.");
                return;
            }
            Path inputFile = Paths.get(inputPathStr);

            //Зчитування файлу
            List<String> lines;
            try {
                lines = fh.readAllLines(inputFile);
            } catch (IOException e) {
                System.err.println("Помилка при читанні файлу: " + e.getMessage());
                return;
            }

            if (lines.isEmpty()) {
                System.out.println("Файл порожній.");
                return;
            }

            //Знайти рядок з максимальною кількістю слів
            String maxLine = "";
            int maxCount = 0;
            for (String line : lines) {
                String trimmed = line.trim();
                if (trimmed.isEmpty()) {
                    continue;
                }
                String[] words = trimmed.split("\\s+");
                if (words.length > maxCount) {
                    maxCount = words.length;
                    maxLine = line;
                }
            }

            System.out.println("Введіть набір слів для пошуку (через кому або пробіл)");
            System.out.print("Якщо пошук не потрібен — просто натисніть Enter: ");
            String searchLine = scanner.nextLine().trim();

            Set<String> searchSet = new HashSet<>();
            if (!searchLine.isEmpty()) {
                // Розбиваємо за комою або пробілами
                String[] tokens = searchLine.split("[,\\s]+");
                for (String t : tokens) {
                    if (!t.isBlank()) {
                        searchSet.add(t.toLowerCase(Locale.ROOT));
                    }
                }
            }

            List<String> matched = new ArrayList<>();
            if (!searchSet.isEmpty()) {
                for (String line : lines) {
                    String lower = line.toLowerCase(Locale.ROOT);
                    boolean containsAll = true;
                    for (String w : searchSet) {
                        if (!lower.contains(w)) {
                            containsAll = false;
                            break;
                        }
                    }
                    if (containsAll) matched.add(line);
                }
            }

            System.out.println();
            System.out.println("ツРезультат");
            System.out.println("Файл: " + inputFile.toAbsolutePath());
            System.out.println("Рядок з максимальною кількістю слів (" + maxCount + "):");
            System.out.println(maxLine);
            System.out.println();
            if (!searchSet.isEmpty()) {
                System.out.println("Пошуковий набір: " + searchSet);
                System.out.println("Знайдені рядки (" + matched.size() + "):");
                if (matched.isEmpty()) {
                    System.out.println("[не знайдено]");
                } else {
                    for (String l : matched) {
                        System.out.println(l);
                    }
                }
            } else {
                System.out.println("Пошуковий набір порожній — пошуку не виконували.");
            }

            //Запит про шлях і ім'я файлу для збереження
            System.out.println();
            System.out.print("Введіть шлях та ім'я файлу для збереження результату (наприклад: C:\\temp\\result.ser) : ");
            String outPathStr = scanner.nextLine().trim();
            if (outPathStr.isEmpty()) {
                System.out.println("Шлях для збереження не задано — пропускаємо збереження.");
                return;
            }
            Path outFile;
            try {
                outFile = Paths.get(outPathStr);
            } catch (InvalidPathException ipe) {
                System.err.println("Неправильний шлях: " + ipe.getMessage());
                return;
            }

            Result result = new Result(inputFile.toAbsolutePath(), maxLine, maxCount, searchSet, matched);

            try {
                fh.saveResult(outFile, result);
                System.out.println("Результат успішно збережено у файл: " + outFile.toAbsolutePath());
            } catch (IOException ioe) {
                System.err.println("Помилка при збереженні результату: " + ioe.getMessage());
            }

        } catch (Exception ex) {System.err.println("Сталася несподівана помилка: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
