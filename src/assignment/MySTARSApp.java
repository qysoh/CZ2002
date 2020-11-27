package assignment;

import java.util.*;
import java.io.IOException;
import java.text.ParseException;

/**
 * Displays specific UI to user based on user login, while constantly prompting user to choose his next course of action
 * @version 1.0
 * @since 2020-11-24
 */
public class MySTARSApp {
	public static void main (String args[]) throws ParseException, IOException{
		 AllTXT.readCourses();	
		 AllTXT.readRegisteredCourses();	
		 AllTXT.readStudents();	
		 AllTXT.readIndex();	
		 AllTXT.readLessons();
		 mainMenu();
	}
	
/**
 * Displays main menu UI to user, and prompts user to choose to log in as either admin or student
 * @throws ParseException If string input is in incorrect format
 * @throws IOException If an input or output exception occurred
 */
public static void mainMenu() throws ParseException, IOException {
	Scanner sc=new Scanner(System.in);
	int choice=0;
	try {
		System.out.println("======================================================================================================================================================");
	System.out.println("1. Admin login \n2. Student login \n3. Exit");
	System.out.println("======================================================================================================================================================");
	choice=sc.nextInt();
	}
	catch (Exception e) {
		System.out.println("Invalid Choice! Try Again");
		mainMenu();
		System.exit(0);
	}
	switch(choice)
	{
	case 1: try
	{
		AdminMgr ad=new AdminMgr();
		ad.adminApp();
	}catch (Exception e) 
    {
      e.printStackTrace();
     }   
	        break;
	        
case 2: try	
	{	
		Student loggedin = StudentAuthentication.Authentication();
		if(loggedin!=null) {
			boolean check= StudentAuthentication.checkValidAccessPeriod(loggedin);
			if (check) studentMenu(loggedin);
			else if (!check) mainMenu();
		}			
	}catch (Exception e) 	
    {	
      e.printStackTrace();	
	 }  
case 3:
System.exit(0);

	default:System.out.println("Wrong input!");
	mainMenu();
		    
	}
}

/**
 * Displays admin menu UI to admin, and prompts admin to choose an action to carry out
 * @throws IOException If an input or output exception occurred
 * @throws ParseException If string input is in incorrect format
 */
public void AdminMenu() throws IOException, ParseException //change back to not static
{
	Scanner sc=new Scanner(System.in);
	int ch=0;
	AdminMgr ad=new AdminMgr();
	do
	{
		System.out.println("======================================================================================================================================================");	
	System.out.println("Welcome Admin. What would you like to do?");
	System.out.println("1. Add Student \n2. Add Course \n3. Update Course \n4. Edit Access Period \n5. Check Vacancy"
			+ "\n6. Print Student List By Index \n7. Print Student List By Course \n8. Logout" );
	System.out.println("======================================================================================================================================================");
    ch=sc.nextInt();

switch(ch)
{

case 1: try {
    ad.AddStudent();
} catch (Exception e) 
{
  e.printStackTrace();
 } 
	     break;
case 2: try {
	          ad.addingCourse();
            } catch (Exception e) 
            {
	          e.printStackTrace();
             } 
	         break;

case 3: try {
	         ad.updateCourse();
            } catch(Exception e)
            {
            	e.printStackTrace();
            }
            break;
case 4: try {
	         ad.editAccessPeriod();
            } catch(Exception e)
            {
	          e.printStackTrace();
            }
		break;
case 5: 
	    try {
		     ad.findVacancy();
            } catch(Exception e)
            {
           	 e.printStackTrace();
            }
		break;
case 6:  try {
        	 ad.printStudentsByIndex();
             } catch(Exception e)
             {
	          e.printStackTrace();
             }
        	  break;

case 7: try {
             ad.printStudentsByCourse();
            } catch(Exception e)
            {
	         e.printStackTrace();
            }
             break;
            	
case 8: mainMenu();
        break;
default: 
	System.out.println("Incorrect Input! Please try again.");
	AdminMenu();
}
}while(ch>=1 && ch<=8);
}		

/**
 * Displays student menu UI to student, and prompts student to choose an action to carry out
 * @param loggedin Student currently logged in
 * @throws Exception If string input is in incorrect format
 */
public static void studentMenu(Student loggedin) throws Exception {
	
	int ch=0;

	Scanner sc = new Scanner (System.in);
	do {
		System.out.println("======================================================================================================================================================");
		System.out.println("1. Add Course \n" + "2. Drop Course \n" + "3. Check/Print Courses Registered\n" +
				"4. Check Vacancies Available \n" + "5. Change Index Number of Course\n"+ 
				"6. Swap Index Number with another Student\n"
				+ "7. Change Password\n"+"8. Logout");
		System.out.println("======================================================================================================================================================");
		try {
		System.out.print("Enter the number of your choice: ");
		ch = sc.nextInt();
		}
		catch (Exception e){ 
			System.out.println("Invalid choice. Please Try Again");
			studentMenu(loggedin);
			System.exit(0);
			break;
			};
		
		switch(ch) {
		case 1:
			try {
			System.out.println("Enter Course Code: ");
			String courseno = sc.next().toUpperCase();
			System.out.println("Enter Index Number: ");
			String IndexNum = sc.next().toUpperCase();
			StudentMgr.addCourse(IndexNum,loggedin,courseno);
			}
			catch (Exception e) {
				e.printStackTrace();
			}  
			break;
		case 2:
			try {
			System.out.println("Enter Course Code: ");
			String coursecode = sc.next().toUpperCase();
			System.out.println("Enter Index: ");
			String index = sc.next().toUpperCase();
			StudentMgr.dropCourse(coursecode, loggedin,index);
			}
			catch (Exception e) {
				e.printStackTrace();
			}  
			break;
		case 3:
			try {
			StudentMgr.printCourseReg(loggedin);
			}
			catch (Exception e) {
				e.printStackTrace();
			}  
			break;
		case 4:
			try {
			System.out.println("Enter Course Code: ");
			String courseCode = sc.next().toUpperCase();
			System.out.println("Enter Index Number: ");
			String IndexNum2 = sc.next().toUpperCase();
			StudentMgr.checkVacancies(IndexNum2,courseCode);
			}
			catch (Exception e) {
				e.printStackTrace();
			}  
			break;
		case 5:
			try {
			System.out.println("Enter Course Code: ");
			String courseNum = sc.next().toUpperCase();	
			System.out.println("Enter Existing Index Number: ");
			String IndexNum2 = sc.next().toUpperCase();
			System.out.println("Enter New Index Number: ");
			String IndexNum3 = sc.next().toUpperCase();
			StudentMgr.changeCourseIndex(loggedin, IndexNum2, IndexNum3, courseNum);
			}
			catch (Exception e) {
				e.printStackTrace();
			}  
			break;
		case 6:
			try {
			System.out.println("Enter Course Code: ");
			String courseNum = sc.next().toUpperCase();	
			System.out.println("Enter Existing Index Number: ");
			String Index = sc.next().toUpperCase();
			System.out.println("Enter Student ID of Student you want to swap with: ");
			String stud = sc.next().toUpperCase();
			System.out.println("Enter the Student's Existing Index to swap with: ");
			String Index1 = sc.next().toUpperCase();
			StudentMgr.swapStudent(loggedin, stud, Index, Index1, courseNum);
			}
			catch (Exception e) {
				e.printStackTrace();
			}  
			break;
		case 7:
			StudentMgr.changePassword(loggedin.getUsername());
			break;
		case 8:
			System.out.println("Thank you and See you again!");
			System.exit(0);
			break;
		default:
			System.out.println("Invalid Choice. Please Try Again");
			studentMenu(loggedin);
			break;
		}
	} while(ch>0 && ch<8);		
	}

	}