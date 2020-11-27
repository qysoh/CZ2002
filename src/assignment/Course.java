package assignment;
import java.util.*;
public class Course{
    /*
     *attributes of course class
     */
    private String courseCode; //course code for each module
    private String courseName; //course name for each module
    private int AU; //AU for each module
    private String school; //school that offers the module
    private String courseType; //list of indexes for the course students can register for
    private Calendar examDate;

    /**
     * course constructor
     * @param courseCode
     * @param courseName
     * @param AU
     * @param school
     * @param courseType
     * @param examDate
     */
    public Course (String courseCode, String courseName, int AU, String school, String courseType, Calendar examDate){
        this.courseCode=courseCode;
        this.courseName=courseName;
        this.AU=AU;
        this.school=school;
        this.examDate=examDate; 
        this.courseType=courseType;
    }
   
    public String getcourseCode1(){
        return courseCode;
    }
    /**
     * change course code
     * @param courseCode
     */
    public void setcourseCode1(String courseCode){
        this.courseCode=courseCode;
    }
    /**
     * retrieve course name
     */
    public String getcourseName1(){
        return courseName;
    }
    /**
     * set course name
     */
    public void setcourseName1(String courseName){
        this.courseName=courseName;
    }
    /**
     * retrieve AU
     */
    public int getAU(){
        return AU;
    }
    /**
     * set AU
     */
    public void setAU(int au){
        AU = au;
    }
    /**
     * retrieve school
     */
    public String getschool1(){
        return school;
    }
    /**
     * set school
     */
    public void setschool1(String school){
        this.school=school;
    }
    /**
     * retrieve course type
     */
    public String getcourseType1() {
		return courseType;
	}
	/**
     * set course type
     */
	public void setcourseType1(String courseType) {
		this.courseType = courseType;
	}
    /**
     * retrieve exam date
     */
	public String getExamDate(){
		return CalendarMgr.dateTimeStr(examDate);
    }
	public Calendar getExamDateCal(){
		return examDate;
	}
    /**
     * set exam date
     */
	public void setExamDate(Calendar examDate){
		this.examDate = examDate;
    }
    
   
	}

