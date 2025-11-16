package org.example;
import java.io.*;

public class FileManager {

    public static void saveShapes(Shape[] shapes, String path) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(shapes);
        }
    }

    public static Shape[] loadShapes(String path) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            return (Shape[]) ois.readObject();
        }
    }
}

