package lab4;

public class Rectangle implements Shape {
	private double length;
	private double width;

	
	public Rectangle(double Length, double Width) {
		length = Length;
		width = Width;
	}
		
	public double calcArea() {
		return width * length;
	}
		
	}

