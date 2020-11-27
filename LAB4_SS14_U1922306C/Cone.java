package lab4;

import static java.lang.Math.sqrt;

public class Cone implements Shape {
    private int radius, height;

    public Cone(int radius, int height) {
        this.radius = radius;
        this.height = height;
    }

    public double calcArea() {
    	Circle circle = new Circle(radius);
    	double l = sqrt(height*height + radius*radius);
        return 3.14159 * radius *l + circle.calcArea();
    }
}
