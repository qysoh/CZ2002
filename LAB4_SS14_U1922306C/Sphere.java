package lab4;

public class Sphere implements Shape {
    private int radius;

    public Sphere(int radius) {
        this.radius = radius;
    }

    public double calcArea() {
        Circle circle = new Circle(radius);

        return 4 * circle.calcArea();
    }
}
