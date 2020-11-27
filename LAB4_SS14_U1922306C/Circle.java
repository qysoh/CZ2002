package lab4;

public class Circle implements Shape {
	private double radius;
	public final double pi = 3.14159;
	
	public Circle (double radius) {
		this.radius = radius;
	}
		
	public double calcArea() {
		return pi * radius * radius;
	}
	}

