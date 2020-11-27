package assignment;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class AdminMgr extends AllTXT {
	public static final String SEPARATOR = "-";
Scanner sc=new Scanner(System.in);
MySTARSApp app=new MySTARSApp();

/**
 * Prompts user to enter correct admin password with only 3 chances
 * @throws IOException If an input or output exception occurred
 * @throws ParseException
 */
public void adminApp() throws IOException, ParseException
	{
		
	int I_count=0;
	System.out.println("Enter password:");
	String A_password=sc.next();
	while(A_password.equals("letmein")!=true)
		{
		if(I_count==3) 
		{System.out.println("Too many attempts. You have been locked out.");
		System.exit(0);
	    }
		I_count+=1;
		System.out.println("Incorrect Password. Please re-enter: ");
		A_password=sc.next();	
	}
	
	app.AdminMenu();
}
    
    /**
     * Prompts user for all details required of new student. New student is added into studentlist.
     * Calls saveStudent method to save new student into text file.
     * @throws Exception
     */
	public void AddStudent() throws Exception
    {
    	char ch='Y';
    	String name;
    	String id=null;
    	char gender;
    	String nationality;
    	String email;
    	String username;

    	
    	do
    	{
	        
    	System.out.println(":::STUDENT DETAILS:::");
    	int cnt=0;
    	System.out.println("Enter Student Matric ID: ");
	    id=sc.next().toUpperCase();
    	for(int i=0;i<studentlist.size();i++)
    	{	
    		if(id.equalsIgnoreCase(studentlist.get(i).getMatricNumber()))
    		{
    			System.out.println("This matric number has already been taken! Please re-enter another matric number.");
    			break;
    		}
    		else cnt+=1;
    		
    	}
    	if(cnt==studentlist.size())
    	{
    	System.out.println("Enter Student Name: ");
		System.out.print(sc.nextLine());
    	name=sc.nextLine().toUpperCase();
    	
    	System.out.println("Enter Student Gender (M/F): ");
    	gender=sc.next().charAt(0);
    	
     	System.out.println("Enter Student Nationality:");
    	nationality=sc.next().toUpperCase();
    	
    	System.out.println("Enter Student Personal Email Address:");
    	email=sc.next().toUpperCase();
    	
    	Generate g=new Generate();
    	username=g.generateUsername(email); // ntu username generated
        double hashval=g.CreatePass();  
        
    	Calendar accessStart = CalendarMgr.correctDateTime("Student Access Start Time");
		Calendar accessEnd = CalendarMgr.correctDateTime("Student Access End Time");
    	
    	Student x = new Student(name,username,hashval,gender,nationality,email,id, accessStart, accessEnd);
    	
		studentlist.add(x);
        saveStudent();
    	
    	System.out.println("Would you like to add another student?(Y/N)");
    	ch=sc.next().charAt(0);
    	}
    	else 
    	{
    		AddStudent();
    		ch='N';
    	}
    	}while(ch=='Y' || ch=='y');
		System.out.println("===============List of All Students===============");
    	for(int i=0; i<studentlist.size(); i++) {
    		System.out.println("Matric Number: "+studentlist.get(i).getMatricNumber()+"\tStudent Name: "+studentlist.get(i).getName());
    	}
    	app.AdminMenu();
    }

	/**
	 * Prompts user for all details required of new course. New course is added into courselist.
     * Calls addIndex method to add corresponding indexes of new course as details of course in courselist.
     * Calls saveCourses method to save new course into text file.
	 * @throws Exception
	 */
	public void addingCourse() throws Exception
	{
		String courseName,courseCode=null;
		int AU=0;
		boolean flag=true;
		char ynCourse='Y';
		do
		{
		System.out.print("New Course Code: \n");
		String courseEntry = sc.next().toUpperCase();
		
		
		for(int i=0;i<courselist.size();i++)
		{
			
			courseCode = courselist.get(i).getcourseCode1();
			if(courseCode.equalsIgnoreCase(courseEntry))
			{
				System.out.println("Course Code already exists! Please enter a new Course Code.");
				addingCourse();
				flag=false;
			}
		}
		
			if(flag==true)
			{
				System.out.println("New Course Name: "); 
				System.out.print(sc.nextLine());
				courseName = sc.nextLine().toUpperCase();
				while(true){
		        	try{
		        		System.out.print("Number of AUs: "); 
		        		AU = sc.nextInt();
		        		flag=false;
		        		break;
		        	} catch (Exception e){
		        		sc.next();
		        		System.out.println("Invalid input!");
		        	}
		        
	        	}
	        
	        System.out.print("School that offers the course (eg: SCSE): "); 
	        String school = sc.next().toUpperCase();
			System.out.print("Course type: ");
			String courseType = sc.next().toUpperCase();
			
		
			Calendar examDate = CalendarMgr.correctDateTime("Exam Date and Exam Time");
		   
			Course course = new Course(courseEntry, courseName, AU, school, courseType, examDate);
			
			courselist.add(course);
			
			
			//adding indexes
			addIndex(courseEntry);
			
			}
			System.out.println("Would you like to add another Course? (Y/N)");
			ynCourse=sc.next().charAt(0);
	    	}while(ynCourse=='y' || ynCourse=='Y');
		saveCourses();
		System.out.println("===============List of All Courses===============");
    	for(int i=0; i<courselist.size(); i++) {
    		System.out.println("Course Code: "+courselist.get(i).getcourseCode1()+"\tCourse Name: "+courselist.get(i).getcourseName1());
    	}
	}	
	
	/**
	 * Prompts user for all details required of new index of course. New index is added into indexlist.
	 * Calls addLesson method to add corresponding lessons of new index as details of index in indexlist.
     * Calls saveIndex method to save new index into text file.
	 * @param courseid Course code of course which new index belongs to
	 * @throws Exception
	 */
public void addIndex(String courseid) throws Exception
	{
		String indexNum, tutGroup;
		int vacancy=0, waitingList = 0;
		char ynIndex='Y';
		do {
		System.out.println("\nFor Course -- " + courseid + ": ");
	    	System.out.print("New Index Number: \n");
			indexNum = sc.next();
			
			System.out.println("Enter Tutorial Group Name (eg. SE1): "); 
			tutGroup = sc.next().toUpperCase();

			boolean isTrue = true;
			while(isTrue) {
				try {
					System.out.println("Number of Vacancies: ");
					vacancy = sc.nextInt();
					sc.nextLine();
					isTrue=false;
					waitingList = 0;
					System.out.println("Number of Students Waiting: 0");
				}
				catch (Exception e){
					sc.nextLine();
					System.out.println("Invalid input!"); 
				}
			}
			ArrayList<String> addqueue = new ArrayList<String>();
			Index updateIndex = new Index(courseid, indexNum, tutGroup, vacancy, waitingList,addqueue);
		    indexlist.add(updateIndex);
		    
		   addLesson(courseid, indexNum);
		   System.out.println("Would you like to add another Index? (Y/N)");
		   ynIndex=sc.next().charAt(0);
		   
		}while(ynIndex=='y' || ynIndex=='Y');
		saveIndex();
	}

	/**
	 * Prompts user for all details required of new lesson. New lesson is added into lessonlist.
     * Calls saveLesson method to save new lesson of an index of a course into text file.
	 * @param courseid Course code of code which contains index that new lesson belongs to
	 * @param indexNum Index which new lesson belongs to
	 * @throws Exception
	 */
    public void addLesson(String courseid, String indexNum) throws Exception
    {
    	String lessonDay = null, lessonType, lessonLoc;
		int day = 0;
	    
	    char ynLesson ='Y';
        System.out.println("\nFor Course -- " + courseid + ": " + " (" + indexNum + ")");
        do {
			do {
				try {
        		System.out.println("Choose Lesson Day: \n1. MONDAY \n2. TUESDAY \n3. WEDNESDAY \n4. THURSDAY"
					+ "\n5. FRIDAY \n6. SATURDAY \n7. SUNDAY"); 
    			day = sc.nextInt();
			 switch (day) {
	            case 1: lessonDay = "MONDAY"; break;
	            case 2: lessonDay = "TUESDAY"; break;
	            case 3: lessonDay = "WEDNESDAY"; break;
	            case 4: lessonDay = "THURSDAY"; break;
	            case 5: lessonDay = "FRIDAY"; break;
	            case 6: lessonDay = "SATURDAY"; break;
	            case 7: lessonDay = "SUNDAY"; break;
	            default: 
	            	System.out.println("Invalid day range");
	            	break;
			 } 
			}  catch(Exception e) {System.out.println("Invalid input");
				System.out.println("Program terminating..");
				break;
			              }
        	}while (day<=0 || day>7);
			
			
			Calendar lessonStartTime = CalendarMgr.correctTime("Enter Lesson Start Time ");
			Calendar lessonEndTime = CalendarMgr.correctTime("Enter Lesson End Time ");

	
			System.out.println("Enter Lesson Type (eg. LEC/TUT): "); 
			lessonType = sc.next().toUpperCase();
			
			System.out.println("Enter Lesson Location: "); 
			lessonLoc = sc.next().toUpperCase();
	    	
	    	
    	
		Lesson updateLesson = new Lesson(courseid, indexNum, lessonDay, lessonStartTime, lessonEndTime, lessonType, lessonLoc);
		lessonlist.add(updateLesson);
		
		System.out.println("Would you like to add another Lesson?(Y/N)");
    	ynLesson=sc.next().charAt(0);
        }while(ynLesson=='y' || ynLesson=='Y');
        saveLesson();
    }
    
   /**
    * Checks whether new index is an existing index of a course
    * @param courseCode Course code of course which the indexes belong
    * @param index New index number
    * @return
    */
   public boolean checkIndex(String courseCode, String index)
   {
	   for(int i=0;i<indexlist.size();i++)
	   {
		   if(courseCode.equalsIgnoreCase(indexlist.get(i).getcourseCode1()) && index.equalsIgnoreCase(indexlist.get(i).getindexNum()))
		   {
			   return false;
		   }
	   }
	   return true;
   }
   
   /**
    * Allows for updates to be made to existing ArrayLists
    * Prompts user to choose what he would like to update
    * @throws Exception
    */
	public void updateCourse() throws Exception
	{
		System.out.println("Enter the Course Code of the Course to Update: ");
		 String courseEntry=sc.next().toUpperCase();
		 boolean entry=false;

		 for(int i=0;i<courselist.size();i++)
		 {
			 if(courseEntry.equalsIgnoreCase(courselist.get(i).getcourseCode1()))
			 {
				int ch;
				do {
				try {
					System.out.println("Choose Field to Update: \n1. Course Name \n2. School \n3. Course Type"
							+ "\n4. Index Number \n5. Vacancy \n6. Add Index \n7. Add Lesson \n8. Back to Admin Menu"); 
 			ch = sc.nextInt();
			 switch (ch) {
			 case 1: 
	            	System.out.println("Enter Updated Course Name: "); 
	        		System.out.print(sc.nextLine());
	        		String newName=sc.nextLine().toUpperCase();
	            	courselist.get(i).setcourseName1(newName);
	            	saveCourses();
	            	break;
	            case 2: 
	            	System.out.println("Enter Updated School that offers the course (eg: SCSE): "); 
	 		        courselist.get(i).setschool1(sc.next().toUpperCase());
	 		        saveCourses();
	            	break;
	            case 3: 
	            	System.out.println("Enter Updated Course type: ");
					courselist.get(i).setcourseType1(sc.next().toUpperCase());
					saveCourses();
	            	break;
	            case 4: 
	            	String newIndexNum = null;
	            	boolean entry1 = false;
	            	
	            	System.out.println("Enter Existing Index Number To Change: ");   
	                String indexNum = sc.next();
	                
	                for(int j=0;j<indexlist.size();j++) {
	                // if course code in index list = courseEntry
       				 if(indexlist.get(j).getcourseCode1().equalsIgnoreCase(courseEntry)){
       					 //if index number in index list = indexnum
       						 if(indexlist.get(j).getindexNum().equalsIgnoreCase(indexNum)) {
       							 	entry1=true;
			        				 System.out.println("Enter Updated Index Number: ");   
			        				 newIndexNum = sc.next();   	 
			        				 boolean checkIndex=checkIndex(courseEntry,newIndexNum);
			        				 if(checkIndex)
			        				 {
			        					 indexlist.get(j).setindexNum(newIndexNum);	
				        				 System.out.println("Index Number has been Updated!");
				        				
				        				 break;
			        				 }
			        				 else
			        				 {
			        					 System.out.println("Index cannot be updated to an existing index!");
			        					 break;
			        				 }

		                		}
		                	}
	                }
	                if(entry1==false)
	                {
	                	System.out.println("Invalid Index Number! Please try again!");
	                }
	                   	for(int k=0;k<lessonlist.size();k++) {
		        				 if(lessonlist.get(k).getcourseCode1().equalsIgnoreCase(courseEntry)){
		        						 if(lessonlist.get(k).getIndexNum().equalsIgnoreCase(indexNum)) {
		        							 lessonlist.get(k).setIndexNum(newIndexNum);
		        						 }
   				 		}
   			}
	                   	for(int m=0;m<registeredCourseslist.size();m++) {
	        				 if(registeredCourseslist.get(m).getCourseCode().equalsIgnoreCase(courseEntry)){
	        						 if(registeredCourseslist.get(m).getIndexNumber().equalsIgnoreCase(indexNum)) {
	        							 registeredCourseslist.get(m).setIndexNumber(newIndexNum);
	        						 }
	        				 }
	                   	}
	                   	saveIndex();
	                   	saveLesson();
	                   	saveRegisteredCourse();
	            	break;
	            case 5:
	            	boolean entry3 = false;
	            	System.out.println("Enter Existing Index Number To Change Vacancy: ");   
	                indexNum = sc.next();
	                for(int j=0;j<indexlist.size();j++) {
       				 if(indexlist.get(j).getcourseCode1().equalsIgnoreCase(courseEntry)){
       						 if(indexlist.get(j).getindexNum().equalsIgnoreCase(indexNum)) {
       							entry3=true;
			        				 boolean isTrue = true;
			        					while(isTrue) {
			        						try {
			        				 System.out.println("Enter Updated Vacancy: ");   
			        				 int newVacancy = sc.nextInt();
			        				 if (newVacancy==0) {
			        				 indexlist.get(j).setVacancy(newVacancy);	 
			        				 }
			        				 else {
				        				indexlist.get(j).setVacancy(newVacancy);
				        				saveIndex();
				        				int count = indexlist.get(j).getWaitingList();
				        				while (count>0) {
				        					StudentMgr.updateWaitlist();
				        					count--;
				        				}
			 							System.out.println("Vacancy and Waitlist have been updated!");
			 							break;
			        				 } 
			        						}
			        				 catch (Exception e){
			        						sc.nextLine();
			        						System.out.println("Invalid input!"); 
			        						break;
			        					}
			        						
		                			} break;
		                		}
		                	}
       				 }
	                if(entry3==false)
	                {
	                	System.out.println("Invalid Index Number! Please try again!");
	                
	                }
	            	break;
	            case 6: addIndex(courseEntry);
	            		break;
	            case 7: System.out.println("Course ID: "+courseEntry);
	            System.out.println("Enter Index number:");
	            indexNum=sc.next().toUpperCase();
	            boolean flag=false;
	            for(int j=0;j<indexlist.size();j++)
	            {
	            	if(indexlist.get(j).getindexNum().equalsIgnoreCase(indexNum))
	            	{
	            		addLesson(courseEntry, indexNum);
	            		flag=true;
	            		break;
	            	}
	            }
	            if(flag==false)  
	            System.out.println("Index number does not exist!");
	            	break;
	            case 8:app.AdminMenu();
	            	break;
	            default: 
	            	System.out.println("Invalid input. Please try again!");
	            	break;
			 } 
			}  catch(Exception e) {System.out.println("Invalid input");
				System.out.println("Program terminating..");
				break;
			};
				}while (ch>0 && ch<8);
				}
						 
					 }
					 if (entry==false) {
						 System.out.println("Course code does not exist! Try again.");
					 }
					
			}

			

/**
 * Prompts user for new access start time and end time of a specific student
 * @throws Exception
 */
public void editAccessPeriod() throws Exception
{
	boolean entry = false;
	 System.out.println("Enter the Matric Number of the Student: ");
	 String matricNo=sc.next();
	 for(int i=0;i<studentlist.size();i++)
	 {
		 String matricNum= studentlist.get(i).getMatricNumber();
		 if(matricNum.equalsIgnoreCase(matricNo))
		 {	entry = true;
			 studentlist.get(i).setAccessStart(CalendarMgr.correctDateTime("New Access Start Time"));
			 studentlist.get(i).setAccessEnd(CalendarMgr.correctDateTime("New Access End Time"));
		 }
		 
	 }
	 if (entry == false) {
			 System.out.println("Incorrect Matric Number! Please try again.");
			 editAccessPeriod();
		 
	 }
	 
	 saveStudent();
}

/**
 * Searches for number of vacancies for a specific index of a course
 * @throws Exception
 */
public void findVacancy() throws Exception
{
	System.out.println("Enter the Course ID: ");
    String courseEntry = sc.next().toUpperCase();
   boolean entry1=false;
   boolean entry2=false;
    System.out.println("Enter the Index Number: ");
    String indexNum = sc.next();
   
    for(int i=0;i<indexlist.size();i++)
    {
    	if(indexlist.get(i).getcourseCode1().equalsIgnoreCase(courseEntry))
    	{ entry1=true;
    		
    		if(indexlist.get(i).getindexNum().equalsIgnoreCase(indexNum))
    		{
    			System.out.println("Course ID: "+courseEntry+"\nIndex: "+indexNum+"\nVacancy: "+indexlist.get(i).getVacancy());
    			entry2=true;
    		}
    	}
    	
    }
    
    if(entry1==false)
    {System.out.println("Course ID does not exist! Please re-enter");
    findVacancy();
    }
    else if(entry2==false)
    {
    	System.out.println("Index number does not exist! Please re-enter");
    	findVacancy();
    }
}

/**
 * Prompts user for course code, and prints student list according to the specified course code
 * @throws Exception
 */
public void printStudentsByCourse() throws Exception
{
  boolean entry=false;
  System.out.println("Enter Course Code: ");   
	String courseEntry = sc.next().toUpperCase();
  for(int i=0;i<courselist.size();i++)
  {
	if(courseEntry.equalsIgnoreCase(courselist.get(i).getcourseCode1()))
	{
	entry = true;
	String course=courselist.get(i).getcourseName1();
	System.out.println("Course Code: "+courseEntry + "\tCourse Name: "+ course.toUpperCase());
	System.out.println("----------------------------------------------");
	for(int j=0;j<registeredCourseslist.size();j++)
	{
	   if(courselist.get(i).getcourseCode1().equalsIgnoreCase(registeredCourseslist.get(j).getCourseCode()) && registeredCourseslist.get(j).getRegisterStatus().equalsIgnoreCase("REGISTERED") )
	   {
		 String id=registeredCourseslist.get(j).getMatricID();
		 for(int k=0;k<studentlist.size();k++)
		 {
		   if(id.equalsIgnoreCase(studentlist.get(k).getMatricNumber()))
					   {
					   String name=studentlist.get(k).getName();
					   System.out.println("Name: "+name+"\tMatric ID: "+id);
					   break;
					   }
		 }
	   }
	  
	}
	System.out.println();
	}
  }
  if (entry==false) {
	System.out.println("Course code does not exist! Try again.");
	printStudentsByCourse();
  }
}

/**
 * Prompts user for index number of a course, and prints student list according to the specified index number
 * @throws Exception
 */
 public void printStudentsByIndex() throws Exception
 {
	 boolean entry=false;
	 boolean entry1=false;
	 boolean entry2=false;
	 System.out.println("Enter Course Code: ");
	 String courseEntry = sc.next().toUpperCase();
	 for(int i=0;i<courselist.size();i++)
	 {
		 if(courseEntry.equalsIgnoreCase(courselist.get(i).getcourseCode1()))
		 {
			 entry = true;
	 
		 System.out.println("Enter Index Number: ");   
	     String indexNo = sc.next();
		 for(int j=0;j<indexlist.size();j++)
		 {
			 
			 if(indexlist.get(j).getindexNum().equalsIgnoreCase(indexNo)) 
			 {
				 entry1 = true;
		 
		 System.out.println("Index Number: "+indexNo);
		 System.out.println("----------------------------------------");

		 for(int k=0;k<registeredCourseslist.size();k++)
		 {
			 if(indexNo.equalsIgnoreCase(registeredCourseslist.get(k).getIndexNumber()) && registeredCourseslist.get(k).getRegisterStatus().equalsIgnoreCase("REGISTERED"))
			 {
				 String matric=registeredCourseslist.get(k).getMatricID();
				 for(int m=0;m<studentlist.size();m++)
				 { 
					 if(matric.equalsIgnoreCase(studentlist.get(m).getMatricNumber()))
					 {
						 String name=studentlist.get(m).getName();
						 System.out.println("Name: "+name+"\tMatric ID: "+matric);
						 entry2=true;
						 break;
					 }
				 }
			 }
			 else if(entry2==true) break;
		 }
		 System.out.println();
	 }
			 else if(entry2==true) break;
 }
 
	 
}
 
	 }
	 if (entry==false) {
		 System.out.println("Course code does not exist! Try again.");
		 printStudentsByIndex();
	 }
	 else if (entry1==false) {
		 System.out.println("Index number does not exist! Try again.");
		 printStudentsByIndex();
	 }
 }
 
}