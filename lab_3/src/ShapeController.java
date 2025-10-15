import shapes.Circle;
import shapes.Rectangle;
import shapes.Shape;
import shapes.Triangle;
import utils.CharInputUtility;

public class ShapeController {

    private final ShapeModel model;
    private final ShapeView view;
    private final CharInputUtility ut;

    public ShapeController(ShapeModel model, ShapeView view, CharInputUtility ut) {
        this.model = model;
        this.view = view;
        this.ut = ut;
    }

    public void execute() {int choice;
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
                    break;case 0:
                    System.out.println("Exiting the application");
                    break;
                default:
                    break;
            }
            if (choice != 0) {
                System.out.println("\nPress any key to continue...");
                new java.util.Scanner(System.in).nextLine();
            }
        } while (choice != 0);
    }
}