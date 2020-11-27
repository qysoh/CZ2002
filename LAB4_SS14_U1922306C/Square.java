package lab4;

public class Square implements Shape {
	private double length;
	
	public Square(double Length) {
		length = Length;
	}
		
	public double calcArea() {
		return length * length;
	}
		
	}

