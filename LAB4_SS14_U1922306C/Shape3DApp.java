package lab4;

import java.util.Scanner;

public class Shape3DApp {
	public static void main(String args[]) {
		System.out.println("1. Enter total no. of shapes");
		Scanner sc = new Scanner(System.in);
		int noOfShapes = sc.nextInt();
		
		if (noOfShapes>=0) {			
		Shape[] shapeArray = new Shape[noOfShapes];			
		for (int i=0; i<noOfShapes; i++) {
			System.out.println("1. Sphere \n2. Pyramid  \n3.Cuboid \n4. Cone\n5. Cylinder \nSelect shape " + (i+1));
			int selectShape = sc.nextInt();
			
			if (selectShape>=1 && selectShape<=5) {
			
			switch (selectShape) {
			case 1: 
				System.out.println("Enter radius: ");
				int a = sc.nextInt();
				if (a<0) {
					System.out.println("Invalid Input Please Try Again");
					i--;
					break; }
				else {
				shapeArray[i] = new Sphere(a);
				break; }
				
			case 2:
				System.out.println("Enter length: ");
				int b = sc.nextInt();
				System.out.println("Enter height: ");
				int c = sc.nextInt();
				if (b<0 || c<0) {
					System.out.println("Invalid Input Please Try Again");
					i--;
					break; }
				else {
				shapeArray[i] = new Pyramid(b,c);
				break; }
				
			case 3:
				System.out.println("Enter length: ");
				int d = sc.nextInt();
				System.out.println("Enter width: ");
				int e = sc.nextInt();
				System.out.println("Enter height: ");
				int r = sc.nextInt();
				if (d<0 || e<0 || r<0) {
					System.out.println("Invalid Input Please Try Again");
					i--;
					break; }
				else {
				shapeArray[i] = new Cuboid(d,e,r);
				break; }
			case 4:
				System.out.println("Enter radius: ");
				int f = sc.nextInt();
				System.out.println("Enter height: ");
				int g = sc.nextInt();
				if (f<0 || g<0) {
					System.out.println("Invalid Input Please Try Again");
					i--;
					break; }
				else {
				shapeArray[i] = new Cone(f,g);
				break;	}
			case 5:
				System.out.println("Enter radius: ");
				int h = sc.nextInt();
				System.out.println("Enter length: ");
				int q = sc.nextInt();
				if (h<0 || q<0) {
					System.out.println("Invalid Input Please Try Again");
					i--;
					break; }
				else {
				shapeArray[i] = new Cylinder(h,q);
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
			System.out.printf("Total Area = %.2f ", calArea);
		}
		
		else System.out.println("Invalid input");
		
		
			
		}

		
	}

