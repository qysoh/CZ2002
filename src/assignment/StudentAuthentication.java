package assignment;
import java.util.*;
import java.io.*;

public class StudentAuthentication extends AllTXT {
	
	public static final String SEPARATOR = "-";
	
    /**
     * Verifies that the username and password entered by user is correct and matches data in studentlist
     * @return
     * @throws Exception on incorrect user input
     */
    public static Student Authentication() throws Exception
    {
    	
    	Scanner sc=new Scanner(System.in);
    	System.out.println("Enter username:");
    	String username=sc.next();
    	for(int i=0;i<studentlist.size();i++)
    	{
    		if(username.equalsIgnoreCase(studentlist.get(i).getUsername()))
    		{
    			
    			 //check if the console is in. It does not work with eclipse IDLE.
    		       Console console = System.console();
    		       if (console == null) {
    		           System.out.println("Couldn't get console instance");
    		           System.exit(0);
    		       }

    		       //Input user password
    		       char[] passwordArray = console.readPassword("Enter your password: ");
    		       String password = new String(passwordArray);
    		
    			Generate g = new Generate();
    			double passVal=g.hashing(password);
    			if(passVal==studentlist.get(i).getPassword())
    			{
    				return studentlist.get(i);
    			}
    		}
    	}
    	System.out.println("Incorrect username or password");
    	return null;
    }
    
    /**
     * Verifies whether current date time falls within the allocated access period of the student trying to log in  
     * @param s Student
     * @return
     * @throws Exception when current date time is not within allocated access period
     */
    public static boolean checkValidAccessPeriod(Student s) throws Exception {
        Calendar dateTime = Calendar.getInstance();
        Calendar accessStart = CalendarMgr.strDateTime(s.getAccessStart());
        Calendar accessEnd = CalendarMgr.strDateTime(s.getAccessEnd());
        if (accessStart.equals(dateTime) || accessStart.before(dateTime)&& accessEnd.after(dateTime))
        	return true;
        else{
			System.out.println("You are not allowed to access MySTARS. Please check your allocated Access Period.");
			return false;
		} 
    }
}