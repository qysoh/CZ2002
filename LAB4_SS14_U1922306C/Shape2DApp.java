package lab4;

import java.util.Scanner;

public class Shape2DApp {
	public static void main(String args[]) {
		System.out.println("1. Enter total no. of shapes");
		Scanner sc = new Scanner(System.in);
		int noOfShapes = sc.nextInt();
		
		
		
		if (noOfShapes>=0) {
			Shape[] shapeArray = new Shape[noOfShapes];
		
		for (int i=0; i<noOfShapes; i++) {
			System.out.println("1. Circle \n2. Square \n3.Rectangle \n4.Triangle \nSelect shape " + (i+1));
			int selectShape = sc.nextInt();
			
			if (selectShape>=1 && selectShape<=4) {
			
			switch (selectShape) {
			case 1: 
				System.out.println("Enter radius: ");
				int a = sc.nextInt();
				if (a<0) {
					System.out.println("Invalid Input Please Try Again");
					i--;
					break; }
				else {
				shapeArray[i] = new Circle(a);
				break; }
				
			case 2:
				System.out.println("Enter length: ");
				int b = sc.nextInt();
				if (b<0) {
					System.out.println("Invalid Input Please Try Again");
					i--;
					break; }
				else {
				shapeArray[i] = new Square(b);
				break; }
				
			case 3:
				System.out.println("Enter length: ");
				int c = sc.nextInt();
				System.out.println("Enter width: ");
				int d = sc.nextInt();
				if (c<0 || d<0) {
					System.out.println("Invalid Input Please Try Again");
					i--;
					break; }
				else {
				shapeArray[i] = new Rectangle(c,d);
				break; }
			case 4:
				System.out.println("Enter Base: ");
				int e = sc.nextInt();
				System.out.println("Enter Height: ");
				int f = sc.nextInt();
				if (e<0 || f<0) {
					System.out.println("Invalid Input Please Try Again");
					i--;
					break; }
				else {
				shapeArray[i] = new Triangle(e,f);
				break;	}
			}
			}
			else {
				System.out.println("Invalid input Try again");
				i--;
			}
		}
			
			
		
		double calArea = 0;
		
		for (int j=0; j<noOfShapes;j++) {
			calArea += shapeArray[j].calcArea();
		}
		
		System.out.printf("Total Area = %.2f", calArea);
		}
	
	else System.out.println("Invalid Input");

}
}
