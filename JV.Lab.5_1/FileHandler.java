package org.example;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileHandler implements Serializable {
    private static final long serialVersionUID = 1L;

    public List<String> readAllLines(Path inputFile) throws IOException {
        if (!Files.exists(inputFile)) {
            throw new FileNotFoundException("Файл не знайдено: " + inputFile);
        }
        return Files.readAllLines(inputFile);
    }

    public void saveResult(Path outputFile, Result data) throws IOException {
        Path parent = outputFile.getParent();
        if (parent != null && !Files.exists(parent)) {
            Files.createDirectories(parent);
        }

        try (FileOutputStream fos = new FileOutputStream(outputFile.toFile());
             BufferedOutputStream bos = new BufferedOutputStream(fos);
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(data);
        }
    }
}
