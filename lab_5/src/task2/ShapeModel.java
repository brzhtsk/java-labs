package task2;

import task2.shapes.Circle;
import task2.shapes.Rectangle;
import task2.shapes.Shape;
import task2.shapes.Triangle;

import java.util.*;

public class ShapeModel {

    private List<Shape> shapes;

    public ShapeModel() {
        this.shapes = initializeShapes();
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }

    private List<Shape> initializeShapes() {
        Shape[] result = new Shape[15];
        String[] shapeTypes = {"circle", "rectangle", "triangle"};
        String[] shapeColors = {"red", "green", "yellow", "white", "black", "blue", "purple"};
        Random rand = new Random();

        for (int i = 0; i < 15; i++) {
            switch (shapeTypes[rand.nextInt(3)]) {
                case "circle":
                    result[i] = new Circle(
                            shapeColors[rand.nextInt(7)],
                            rand.nextDouble() * 10 + 1
                    );
                    break;
                case "rectangle":
                    result[i] = new Rectangle(
                            shapeColors[rand.nextInt(7)],
                            rand.nextDouble() * 50 + 1,
                            rand.nextDouble() * 50 + 1
                    );
                    break;
                case "triangle":
                    double a, b, c;
                    do {
                        a = rand.nextDouble() * 50 + 1;
                        b = rand.nextDouble() * 50 + 1;
                        c = rand.nextDouble() * 50 + 1;
                    } while (!(a + b > c && a + c > b && b + c > a));
                    result[i] = new Triangle(
                            shapeColors[rand.nextInt(7)],
                            a, b, c
                    );
                    break;
                default: break;
            }
        }
        return Arrays.asList(result);
    }

    public List<Shape> sortShapesByColorAsc() {
        Comparator<Shape> comparator = Comparator.comparing(Shape::getShapeColor);
        return shapes.stream()
                .sorted(comparator)
                .toList();
    }

    public List<Shape> sortShapesByAreaAsc() {
        Comparator<Shape> comparator = Comparator.comparingDouble(Shape::calcArea);
        return shapes.stream()
                .sorted(comparator)
                .toList();
    }

    public double calcAreaSum() {
        return shapes.stream()
                .mapToDouble(Shape::calcArea)
                .sum();
    }

    public double calcAreaSumOfShape(Class<? extends Shape> c) {
        return shapes.stream()
                .filter(c::isInstance)
                .mapToDouble(Shape::calcArea)
                .sum();
    }
}