package org.example;

import java.io.*;

public class FileManager {

    public static void save(String filePath, TagStats stats) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(stats);
        }
    }

    public static TagStats load(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
            return (TagStats) in.readObject();
        }
    }
}

