package assignment;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;   

public class AllTXT {
	public static final String SEPARATOR = "-";
    public static ArrayList <Index> indexlist = new ArrayList <Index>(); 
    public static ArrayList <Course> courselist = new ArrayList <Course>();
    public static ArrayList <Student> studentlist = new ArrayList <Student>();
    public static ArrayList <Lesson> lessonlist = new ArrayList<Lesson>();
	public static ArrayList<RegisteredCourses> registeredCourseslist = new ArrayList<RegisteredCourses>();	
	
	/**
	 * Reads existing courses and their respective details from text file and adds to courselist
	 * @throws IOException If an input or output exception occurred
	 * @throws ParseException
	 */
    public static void readCourses() throws IOException, ParseException
    {
        ArrayList<String> stringArray = (ArrayList) ReadWriteFile.read("course.txt");
        Calendar examDate = Calendar.getInstance();
        
        for (int i=0; i<stringArray.size(); i++){
        String part = (String) stringArray.get(i);
        StringTokenizer tokenizer = new StringTokenizer(part, SEPARATOR);

        String courseCode = tokenizer.nextToken().trim();
        String courseName = tokenizer.nextToken().trim();
        int AU = Integer.parseInt(tokenizer.nextToken().trim());
        String school = tokenizer.nextToken().trim();
        String courseType = tokenizer.nextToken().trim();
		examDate = CalendarMgr.strDateTime(tokenizer.nextToken().trim());
        
        
        Course course = new Course(courseCode, courseName, AU, school, courseType, examDate);
        courselist.add(course);
    }
   
    }
    
    /**
     * Reads existing students and their respective details from text file and adds to studentlist
     * @throws IOException If an input or output exception occurred
     * @throws ParseException
     */
    public static void readStudents() throws IOException, ParseException{
        ArrayList<String> stringArray = (ArrayList) ReadWriteFile.read("AllStudents.txt");

        for(int i = 0; i<stringArray.size(); i++){
            String part = (String) stringArray.get(i);

            StringTokenizer tokenizer = new StringTokenizer(part, SEPARATOR);
            
            String name = tokenizer.nextToken().trim();
            String username = tokenizer.nextToken().trim();
            Double password = Double.parseDouble(tokenizer.nextToken().trim());
            char gender = tokenizer.nextToken().charAt(0);
            String nationality = tokenizer.nextToken().trim();
            String email = tokenizer.nextToken().trim();
            String matricID = tokenizer.nextToken().trim();
            Calendar accessStart = CalendarMgr.strDateTime(tokenizer.nextToken().trim());
			Calendar accessEnd = CalendarMgr.strDateTime(tokenizer.nextToken().trim());

            Student std = new Student(name, username, password, gender, nationality, email, matricID, accessStart, accessEnd);

            studentlist.add(std);
        }
      
    }
    
    /**
     * Reads existing lessons and their respective details from text file and adds to lessonlist
     * @throws IOException If an input or output exception occurred
     * @throws ParseException
     */
    public static void readLessons() throws IOException, ParseException{
    	
        ArrayList<String> stringArray = (ArrayList) ReadWriteFile.read("lesson.txt");
        for (int i=0; i<stringArray.size(); i++){
            String part = (String) stringArray.get(i);
            
            StringTokenizer tokenizer = new StringTokenizer(part, SEPARATOR);
            
            String courseCode = tokenizer.nextToken().trim();
            String indexNum = tokenizer.nextToken().trim();
            String lessonDay = tokenizer.nextToken().trim();
            Calendar lessonStartTime = CalendarMgr.strTime(tokenizer.nextToken().trim());
            Calendar lessonEndTime = CalendarMgr.strTime(tokenizer.nextToken().trim());
            String lessonType = tokenizer.nextToken().trim();
            String lessonLoc = tokenizer.nextToken().trim();

            Lesson lesson = new Lesson(courseCode, indexNum, lessonDay, lessonStartTime,lessonEndTime, lessonType, lessonLoc);
            lessonlist.add(lesson);
        }
        
    }
    
    /**
     * Reads existing registered courses and their respective details from text file and adds to registeredCourseslist
     * @throws IOException If an input or output exception occurred
     * @throws ParseException
     */
    public static void readRegisteredCourses() throws IOException, ParseException{
    
        ArrayList<String> stringArray = (ArrayList) ReadWriteFile.read("registeredCourse.txt");

    for (int i = 0; i<stringArray.size(); i++){
        String part = (String) stringArray.get(i);
        StringTokenizer tokenizer = new StringTokenizer(part, SEPARATOR);

        String matricID = tokenizer.nextToken().trim();
        String courseCode = tokenizer.nextToken().trim();
        String indexNum = tokenizer.nextToken().trim();;
        String status = tokenizer.nextToken().trim();

        RegisteredCourses course = new RegisteredCourses(matricID, courseCode, indexNum, status);
        registeredCourseslist.add(course);
    }
    }
    
    /**
     * Reads existing indexes of courses and their respective details from text file and adds to indexlist
     * @return
     * @throws IOException If an input or output exception occurred
     * @throws ParseException
     */
    public static ArrayList<Index> readIndex() throws IOException, ParseException {
       	
        ArrayList<String> stringArray = (ArrayList) ReadWriteFile.read("index.txt");
        
        for (int i=0; i<stringArray.size(); i++){
            String part = (String) stringArray.get(i);
            StringTokenizer tokenizer = new StringTokenizer(part, SEPARATOR);
            ArrayList<String> waitqueue = new ArrayList<String>();
            
            String courseCode = tokenizer.nextToken().trim();
            String indexNum = tokenizer.nextToken().trim();
            String tutGroup = tokenizer.nextToken().trim();
            int vacancies = Integer.parseInt(tokenizer.nextToken().trim());
            int waitingList = Integer.parseInt(tokenizer.nextToken().trim());
            while (tokenizer.hasMoreElements()) {
            	String wait = tokenizer.nextToken().trim();
            	waitqueue.add(wait);
            }

            Index index = new Index(courseCode, indexNum, tutGroup, vacancies, waitingList, waitqueue);
            indexlist.add(index);
        }
        return indexlist;
        }
   
    /**
     * Saves new courses and the respective details by appending into text file
     * @throws IOException If an input or output exception occurred
     */
    public static void saveCourses() throws Exception
    {  try
    { 
    	
    	File obj=new File("temp.txt");
    	
    	for(int i=0;i<courselist.size();i++)
    	{
    		 FileWriter filewriter=new FileWriter(obj,true); // the second argument tells the FileWriter to append into the file instead of overwriting
             BufferedWriter bw= new BufferedWriter (filewriter);
             bw.append(courselist.get(i).getcourseCode1()+"-"+ courselist.get(i).getcourseName1()+
           		  "-"+courselist.get(i).getAU()+"-"+ courselist.get(i).getschool1()+"-"
           		  +courselist.get(i).getcourseType1()+"-"+courselist.get(i).getExamDate());
             bw.newLine();
             bw.close();
             filewriter.close();
    	}
    	
    }
    catch(IOException e)
    {
         System.out.println( "IO Error!" + e.getMessage() );
         e.printStackTrace();
         System.exit( 0 );
    }
    File newf=new File("course.txt");
    newf.delete();
    
    Path source= Paths.get("temp.txt");
    Files.move(source, source.resolveSibling("course.txt"));
    }
    
    /**
     * Saves new registered courses and the respective details by appending into text file
     * @throws IOException If an input or output exception occurred
     */
    public static void saveRegisteredCourse() throws Exception
    {
    	try
    	{
    		File obj=new File("temp.txt"); 
    	
    	for(int i=0;i<registeredCourseslist.size();i++)
    	{
    	FileWriter filewriter=new FileWriter(obj,true); // the second argument tells the FileWriter to append into the file instead of overwriting
    	BufferedWriter bw= new BufferedWriter (filewriter);
    	bw.append(registeredCourseslist.get(i).getMatricID()+"-"+ registeredCourseslist.get(i).getCourseCode()+ "-"+registeredCourseslist.get(i).getIndexNumber()+"-"+ registeredCourseslist.get(i).getRegisterStatus());
    	bw.newLine();
    	bw.close();
    	filewriter.close();
    	}
    	}
    	 catch(IOException e)
        {
             System.out.println( "IO Error!" + e.getMessage() );
             e.printStackTrace();
             System.exit( 0 );
        }
    	File newf=new File("registeredCourse.txt");
	    newf.delete();
	    
	    Path source= Paths.get("temp.txt");
	    Files.move(source, source.resolveSibling("registeredCourse.txt"));
      
    }

    /**
     * Saves new students and the respective details by appending into text file
     * @throws IOException If an input or output exception occurred
     */
    public static void saveStudent() throws Exception
    {
    	try
        {
      	 File obj=new File("temp.txt");
       for(int i=0;i<studentlist.size();i++)
       {
    	   
       
        FileWriter filewriter=new FileWriter(obj,true); // the second argument tells the FileWriter to append into the file instead of overwriting
          BufferedWriter bw= new BufferedWriter (filewriter);
          bw.append(studentlist.get(i).getName()+"-"+ studentlist.get(i).getUsername()+"-"+studentlist.get(i).getPassword()+"-"+ 
        		  studentlist.get(i).getGender()+"-"+studentlist.get(i).getNationality()+"-"+studentlist.get(i).getEmail()+"-"+studentlist.get(i).getMatricNumber()
        		  +"-"+studentlist.get(i).getAccessStart()+"-"+studentlist.get(i).getAccessEnd());
          bw.newLine();
          bw.close();
          filewriter.close();

      }
      }
      catch(IOException e)
     {
          System.out.println( "IO Error!" + e.getMessage() );
          e.printStackTrace();
          System.exit( 0 );
     }
    	File newf=new File("AllStudents.txt");
	    newf.delete();
	    
	    Path source= Paths.get("temp.txt");
	    Files.move(source, source.resolveSibling("AllStudents.txt"));
    }
    
    /**
     * Saves new lessons and the respective details by appending into text file
     * @throws IOException If an input or output exception occurred
     */
    public static void saveLesson()throws IOException
    {
    	try
    	{
      File obj=new File("temp.txt");
        for(int i=0;i<lessonlist.size();i++)
        {
        FileWriter filewriter=new FileWriter(obj,true); // the second argument tells the FileWriter to append into the file instead of overwriting
          BufferedWriter bw= new BufferedWriter (filewriter);
          bw.append(lessonlist.get(i).getcourseCode1()+"-"+lessonlist.get(i).getIndexNum()+"-"+ lessonlist.get(i).getLessonDay()+
        		  "-"+lessonlist.get(i).getLessonStartTimeStr()+"-"+lessonlist.get(i).getLessonEndTimeStr()+"-"+lessonlist.get(i).getLessonType()+
        		  "-"+ lessonlist.get(i).getLessonLoc());
          bw.newLine();
          bw.close();
          filewriter.close();
        
        }    
      }
      catch(IOException e)
     {
          System.out.println( "IO Error!" + e.getMessage() );
          e.printStackTrace();
          System.exit( 0 );
     }
    	 File newf=new File("lesson.txt");
 	    newf.delete();
 	    
 	    Path source= Paths.get("temp.txt");
 	    Files.move(source, source.resolveSibling("lesson.txt"));
    }
    
    /**
     * Saves new indexes of courses and the respective details by appending into text file
     * @throws IOException If an input or output exception occurred
     */
    public static void saveIndex()throws Exception
    {
    	try
        	{
        
        		File obj=new File("temp.txt");
        for(int i=0;i<indexlist.size();i++)
        {
          FileWriter filewriter=new FileWriter(obj,true); // the second argument tells the FileWriter to append into the file instead of overwriting
          BufferedWriter bw= new BufferedWriter (filewriter);
          bw.append(indexlist.get(i).getcourseCode1()+"-"+ indexlist.get(i).getindexNum()+
        		  "-"+indexlist.get(i).gettutGroup()+"-"+ indexlist.get(i).getVacancy()+
        		  "-"+ indexlist.get(i).getWaitingList());
          if (indexlist.get(i).getWaitingQueue().size()>0) {
        	  for (String s: indexlist.get(i).getWaitingQueue()) {
        		  bw.append("-"+s);
        	  }
          }
          bw.newLine();
          bw.close();
          filewriter.close();
        }
       
      }
      catch(IOException e)
     {
          System.out.println( "IO Error!" + e.getMessage() );
          e.printStackTrace();
          System.exit( 0 );
     }
    	 File newf=new File("index.txt");
 	    newf.delete();
 	    
 	    Path source= Paths.get("temp.txt");
 	    Files.move(source, source.resolveSibling("index.txt"));
    }

}