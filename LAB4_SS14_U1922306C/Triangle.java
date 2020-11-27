package lab4;

public class Triangle implements Shape{
	private double base;
	private double height;
	
	public Triangle (double Base, double Height) {
		base = Base;
		height = Height;
	}
		
	public double calcArea() {
		return 0.5 * base * height;
	}
		
}