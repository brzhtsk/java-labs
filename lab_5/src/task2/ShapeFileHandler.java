package task2;

import task2.shapes.Shape;

import java.io.*;
import java.util.List;

public class ShapeFileHandler {

    public void saveShapes(List<Shape> shapes, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(shapes);
        }
    }

    public List<Shape> loadShapes(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<Shape>) ois.readObject();
        }
    }
}