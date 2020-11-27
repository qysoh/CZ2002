package lab4;

import static java.lang.Math.sqrt;

public class Pyramid implements Shape{
    private int length, height;

    public Pyramid(int length, int height) {
        this.length = length;
        this.height = height;
    }

    public double calcArea() {
    	double l = sqrt(height*height + (0.5*0.5*length*length));
        Triangle triangle = new Triangle(length, l);
        
        return 4 * triangle.calcArea() + new Square(length).calcArea();
    }
}