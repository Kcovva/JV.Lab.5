package org.example;
import java.io.*;

public class FileManager {

    public static void saveToFile(EncrData data, String path) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(path);
             BufferedOutputStream bos = new BufferedOutputStream(fos);
             FilterOutputStream filterOut = new FilterOutputStream(bos) {
             };
             ObjectOutputStream oos = new ObjectOutputStream(filterOut)) {

            oos.writeObject(data); //Серіалізація за замовчуванням
            oos.flush();
        }
    }

    public static EncrData loadFromFile(String path) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(path);
             BufferedInputStream bis = new BufferedInputStream(fis);
             FilterInputStream filterIn = new FilterInputStream(bis) {
             };
             ObjectInputStream ois = new ObjectInputStream(filterIn)) {

            Object obj = ois.readObject();
            if (obj instanceof EncrData) {
                return (EncrData) obj;
            } else {
                throw new IOException("У файлі не знайдено EncryptedData");
            }
        }
    }
}

