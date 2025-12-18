package task2;

import task2.utils.CharInputUtility;

public class Main {
    public static void main(String[] args) {
        ShapeController controller = new ShapeController(new ShapeModel(), new ShapeView(), new CharInputUtility());
        controller.execute();
    }
}