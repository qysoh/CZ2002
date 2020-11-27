package trying2;
import java.io.*;
import java.util.*;
public class student{
	Scanner sc=new Scanner(System.in);
	public void openDetails(String id)
	{ 
		try
		{
	     BufferedReader br = new BufferedReader(new FileReader("Student"+id+".txt"));
	     String line;
	     while ((line = br.readLine()) != null) { 
	       if((line.contains("Pass:")))continue;
	    	 System.out.println(line);
	     }
	     br.close();
		}
		catch(IOException e)
		{
	System.out.println( "IO Error!" + e.getMessage() );
	e.printStackTrace();
	System.exit( 0 );
		}
		
	}
	public void studentaccess()
	{
	try
	{
   
    
	 System.out.println("Enter student id:");
	 String Sid=sc.next();
	 String printSid="Student ID:"+Sid; 
	 BufferedReader Buff = new BufferedReader(new FileReader("Student"+Sid+".txt"));
     String ActualID = Buff.readLine();
	 if(printSid.equals(ActualID))
	 {
		System.out.println("Enter student password:");
		String Spassword="Pass:"+sc.next();
		String Actualpassword =Buff.readLine();
		if(Spassword.equals(Actualpassword))
		{
			openDetails(Sid);
		}
	 }
	 Buff.close();
	 }
	catch(IOException e)
	{
System.out.println( "IO Error!" + e.getMessage() );
e.printStackTrace();
System.exit( 0 );
	}
	}
}
