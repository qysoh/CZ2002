package lab4;

public class Cuboid implements Shape {
	private double length;
	private double width;
	private double height;

	
	public Cuboid(double Length, double Width, double Height) {
		length = Length;
		width = Width;
		height = Height;
	}
		
	public double calcArea() {
        Rectangle rectangle1 = new Rectangle(height, width);
        Rectangle rectangle2 = new Rectangle(length,height);
        Rectangle rectangle3 = new Rectangle(length, width);
        return 2 * rectangle1.calcArea() + 2* rectangle2.calcArea() + 2*rectangle3.calcArea();
		
	}
		
	}

