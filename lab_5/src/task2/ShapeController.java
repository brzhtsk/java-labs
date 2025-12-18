package task2;

import task2.shapes.Circle;
import task2.shapes.Rectangle;
import task2.shapes.Shape;
import task2.shapes.Triangle;
import task2.utils.CharInputUtility;

import java.util.Scanner;

public class ShapeController {

    private final ShapeModel model;
    private final ShapeView view;
    private final CharInputUtility ut;
    private final ShapeFileHandler fileHandler = new ShapeFileHandler();

    public ShapeController(ShapeModel model, ShapeView view, CharInputUtility ut) {
        this.model = model;
        this.view = view;
        this.ut = ut;
    }

    public void execute() {
        int choice;
        Scanner scanner = new Scanner(System.in);
        do {
            view.printMessage(view.PROMPT);
            choice = ut.readDigit();
            switch (choice) {
                case 1:
                    model.getShapes().forEach(System.out::println);
                    break;
                case 2:
                    view.printMessageAndValue(view.SUM, model.calcAreaSum());
                    break;
                case 3:
                    view.printMessage(view.SHAPE_CHOICE);
                    int ch = ut.readDigit();
                    switch (ch) {
                        case 1:
                            view.printMessageAndValue(view.RECTANGLE_SUM,
                                    model.calcAreaSumOfShape(Rectangle.class));
                            break;
                        case 2:
                            view.printMessageAndValue(view.TRIANGLE_SUM,
                                    model.calcAreaSumOfShape(Triangle.class));
                            break;
                        case 3:
                            view.printMessageAndValue(view.CIRCLE_SUM,
                                    model.calcAreaSumOfShape(Circle.class));
                            break;
                        default: break;
                    }
                    break;
                case 4:
                    model.sortShapesByAreaAsc()
                            .forEach(System.out::println);
                    break;
                case 5:
                    model.sortShapesByColorAsc()
                            .forEach(System.out::println);
                    break;
                case 6:
                    System.out.println("Enter the path to save the shapes (including file name):");
                    String savePath = scanner.nextLine();
                    try {
                        fileHandler.saveShapes(model.getShapes(), savePath);
                        System.out.println("Shapes saved successfully.");
                    } catch (Exception e) {
                        System.out.println("Error saving shapes: " + e.getMessage());
                    }
                    break;
                case 7:
                    System.out.println("Enter the path to load the shapes from (including file name):");
                    String loadPath = scanner.nextLine();
                    try {
                        model.setShapes(fileHandler.loadShapes(loadPath));
                        System.out.println("Shapes loaded successfully.");
                    } catch (Exception e) {
                        System.out.println("Error loading shapes: " + e.getMessage());
                    }
                    break;
                case 0:
                    System.out.println("Exiting the application");
                    break;
                default:
                    break;
            }
            if (choice != 0) {
                System.out.println("\nPress any key to continue...");
                scanner.nextLine();
            }
        } while (choice != 0);
    }
}