package lab3;
import java.util.Scanner;

public class PlaneApp {
	public static void main(String[] args) {
	Scanner sc = new Scanner (System.in);
	Plane plane = new Plane();
	int choice, custID, seatID;
	
		System.out.println("(1) Show number of empty seats");
		System.out.println("(2) Show the list of empty seats");
		System.out.println("(3) Show the list of seat assignments by seat ID");
		System.out.println("(4) Show the list of seat assignments by customer ID");
		System.out.println("(5) Assign a  customer to a seat");
		System.out.println("(6) Remove a seat assignment");
		System.out.println("(7) Exit");
		System.out.println("\n");
	
		do {
		System.out.print("Enter the number of your choice: ");
		choice = sc.nextInt();
		switch (choice) {
		case 1: 
			plane.showNumEmptySeats();
			break;
		case 2:
			plane.showEmptySeats();
			break;
		case 3:
			plane.showAssignedSeats(true);
			break;
		case 4:
			plane.showAssignedSeats(false);
			break;
		case 5:
			System.out.println("Assigning Seat ..");
			System.out.print("Please enter SeatID: ");
			seatID = sc.nextInt();
			if (seatID < 1) {
	    		System.out.println("Only SeatID from 1 to 12 is available!");
	    		break;
	    	}
	    	if (seatID > 12) {
	    		System.out.println("Only SeatID from 1 to 12 is available!");
	    		break;
	    	}
	    	else
	    	System.out.println("Please enter Customer ID: ");
	    	custID = sc.nextInt();
			plane.assignSeat(seatID, custID);
			break;
		case 6:
			System.out.println("Unassigning Seat ..");
	        System.out.println("Please Enter Seat ID: ");
	        seatID = sc.nextInt();
	        if (seatID < 1) {
	    		System.out.println("Only SeatID from 1 to 12 is available!");
	    		break;
	    	}
	    	if (seatID > 12) {
	    		System.out.println("Only SeatID from 1 to 12 is available!");
	    		break;
	    	}
	        plane.unAssignSeat(seatID);
	        break;
		default:
     		System.out.println("Program terminating..");
     		System.exit(0);
				
		}
	} while (choice > 0 && choice != 7);
	}
}
