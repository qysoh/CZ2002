package lab4;

public class Cylinder implements Shape{
    private int radius, length;

    public Cylinder(int radius, int length) {
        this.radius = radius;
        this.length = length;
    }

    public double calcArea() {
        Circle circle = new Circle(radius);
        return 2* circle.calcArea() + (2*3.14159*radius* length);
    }
}
