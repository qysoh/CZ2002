package lab3;
public class Plane {

		private PlaneSeat[] seat = new PlaneSeat [12];
		private int numEmptySeat;
		
		
		public Plane() {
			for (int i=0; i<seat.length; i++) {
				seat[i] = new PlaneSeat(i+1);
			} 
			numEmptySeat = 12;
		}
		//sort seats according to ascending order of cid
		private PlaneSeat[] sortSeats() {
			PlaneSeat temp[] = seat.clone();
			PlaneSeat tempSeat;
			for (int i=0;i<(12-1);i++)
				if (temp[i].getCustomerID()>temp[i+1].getCustomerID())
				{
					tempSeat = temp[i];
					temp[i] = temp[i+1];
					temp[i+1] = tempSeat;
				}
			return temp;
		}
	
		public void showNumEmptySeats() {
			int emptySeats=0;
			for (int i=0; i<seat.length; i++) {
				if(seat[i].isOccupied()==false) {
					emptySeats++;
				}
			}
			System.out.println("There are "+emptySeats+" empty seats.");
		}
		
		public void showEmptySeats() {
			System.out.println("The following seats are empty: ");
			for (int i=0; i<seat.length; i++) {
				if (seat[i].isOccupied()==false) {
					System.out.println("SeatID: " + seat[i].getSeatID());
				}
			}
		}
		public void showAssignedSeats(boolean bySeatId) {
			System.out.println("The seat assignments are as follow: ");
			if (bySeatId == true) {
				for (int i=0; i<seat.length; i++) {
					if (seat[i].isOccupied() == true) {
						System.out.println("SeatID " + seat[i].getSeatID() + " assigned to CustomerID " + 
											seat[i].getCustomerID());
					}
				}
			}
			else {
				PlaneSeat[] temp = sortSeats();
				for (int i=0; i<temp.length; i++) {
					if (temp[i].isOccupied()==true)
						System.out.println("SeatID " + temp[i].getSeatID() + " assigned to CustomerID " + 
						temp[i].getCustomerID());
				}}
			}
		public void assignSeat(int seatId, int cust_id) {
			for(int i=0; i<seat.length; i++) {
				if(seat[i].getSeatID() == seatId) {
					if (seat[i].isOccupied()==true) {
						System.out.println("Seat already assigned to a customer.");
					}
					else {
						seat[i].assign(cust_id);
						numEmptySeat--;
						System.out.println("Seat Assigned!");
					}
				}
			}
		}
		public void unAssignSeat(int seatId) {
			for (int i=0; i<seat.length; i++) {
				if (seat[i].getSeatID() == seatId){
					if (seat[i].isOccupied()==true) {
						seat[i].unAssign();
						numEmptySeat++;
						System.out.println("Seat Unassigned!");
				}
				else {
					System.out.println("Seat is not occupied.");
				}
			}
		}
}
				
	}

