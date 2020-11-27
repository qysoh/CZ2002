package assignment;


import java.util.Calendar;

public class Lesson{
    /*
     *attributes of lesson class
     */
	private String courseCode;
    private String indexNum;
    private String lessonDay;
    private Calendar lessonStartTime;
    private Calendar lessonEndTime;
    private String lessonType;
    private String lessonLoc;
    /**
     * lesson constructor
     * @param courseCode
     * @param indexNum
     * @param lessonDay
     * @param lessonStartTime
     * @param lessonEndTime
     * @param lessonType
     * @param lessonLoc
     */
    public Lesson (String courseCode, String indexNum, String lessonDay, Calendar lessonStartTime, Calendar lessonEndTime, String lessonType, String lessonLoc){
    	this.courseCode = courseCode;
    	this.indexNum = indexNum;
        this.lessonDay = lessonDay;
        this.lessonStartTime = lessonStartTime;
        this.lessonEndTime = lessonEndTime;
        this.lessonType = lessonType;
        this.lessonLoc = lessonLoc;
    }
    /*
     *retrieve and set course code
     */
    public String getcourseCode1()
	{
		return courseCode;
	}
	public void setcourseCode1(String courseCode)
	{
		this.courseCode = courseCode;
	}
    /*
     *retrieve and set index number
     */
    public String getIndexNum(){
        return indexNum;
    }

    public void setIndexNum(String indexNum){
        this.indexNum = indexNum;
    }
    /*
     *retrieve and set day of lesson
     */
    public String getLessonDay(){
        return lessonDay;
    }

    public void setLessonDay(String lessonDay){
        this.lessonDay = lessonDay;
    }
    /*
     *retrieve and set lesson start time
     */
    public Calendar getLessonStartTime(){
        return lessonStartTime;
    }
    public String getLessonStartTimeStr(){
        return CalendarMgr.timeStr(lessonStartTime);
    }

    public void setLessonStartTime(Calendar lessonStartTime){
        this.lessonStartTime = lessonStartTime;
    }
    /*
     *retrieve and set lesson end time
     */
    public Calendar getLessonEndTime(){
        return lessonEndTime;
    }
    public String getLessonEndTimeStr(){
        return CalendarMgr.timeStr(lessonEndTime);
    }

    public void setLessonEndTime(Calendar lessonEndTime){
        this.lessonEndTime = lessonEndTime;
    }
    /*
     *retrieve and set lesson type
     */
    public String getLessonType(){
        return lessonType;
    }

    public void setLessonType(String lessonType){
        this.lessonType = lessonType;
    }
    /*
     *retrieve and set lesson location
     */
    public String getLessonLoc(){
        return lessonLoc;
    }

    public void setLessonLoc(String lessonLoc){
        this.lessonLoc = lessonLoc;
    }

}