package task2.shapes;

import java.util.Locale;

public class Triangle extends Shape {
    private double a;
    private double b;
    private double c;

    public Triangle(String shapeColor, double a, double b, double c) {
        super(shapeColor);
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    @Override
    public double calcArea() {
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "Triangle{" +
                "a=%.1f" +
                ", b=%.1f" +
                ", c=%.1f" +
                ", shapeColor='%s'" +
                '}', a, b, c, getShapeColor());
    }
}