package org.example;
import java.util.*;
import java.util.Map.Entry;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("(˶ᵔ ᵕ ᵔ˶)Введіть URL: ");
            String url = sc.nextLine();

            Map<String, Integer> counts = HtmlTagCounter.countTags(url);
            TagStats stats = new TagStats(counts);

            System.out.println("\n ツТеги в лексикографічному порядку ");
            counts.entrySet()
                    .stream()
                    .sorted(Entry.comparingByKey())
                    .forEach(e -> System.out.println(e.getKey() + " : " + e.getValue()));

            System.out.println("\n ツТеги за зростанням частоти ");
            counts.entrySet()
                    .stream()
                    .sorted(Entry.comparingByValue())
                    .forEach(e -> System.out.println(e.getKey() + " : " + e.getValue()));

            System.out.print("\nВведіть шлях до файлу для збереження: ");
            String filePath = sc.nextLine();

            FileManager.save(filePath, stats);
            System.out.println("Дані збережено у файл.");

            System.out.println("Читаємо файл назад");
            TagStats loaded = FileManager.load(filePath);
            System.out.println("У файлі " + loaded.getTagCounts().size() + " тегів.");

        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}
