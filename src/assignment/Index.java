package assignment;

import java.util.*;

public class Index{
	/**
	 * attributes of index class
	 */
	private String indexNum;
	private String courseCode;
	private String tutGroup;
	private int vacancy;
	private int waitingList;
	private ArrayList<String> waitingqueue;
	
	public Index() {
		this.indexNum = "";
		this.courseCode = "";
		this.tutGroup = "";
		this.vacancy = 0;
		this.waitingList = 0;
		this.waitingqueue = waitingqueue; 
	}
	
	
	/**
	 * index constructor
	 * @param courseCode
	 * @param indexNum2
	 * @param tutGroup
	 * @param vacancy
	 * @param waitingList
	 * @param waitingqueue
	 */
	public Index (String courseCode, String indexNum2, String tutGroup, int vacancy, int waitingList, ArrayList<String> waitingqueue){
		this.courseCode = courseCode;
		this.indexNum = indexNum2;
		this.tutGroup = tutGroup;
		this.vacancy = vacancy;
		this.waitingList = waitingList;
		this.waitingqueue = waitingqueue;
		
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
	public String getindexNum()
	{
		return indexNum;
	}
	public void setindexNum(String indexNum)
	{
		this.indexNum = indexNum;
	}
	/*
	 *retrieve and set tutorial group
	 */
	public String gettutGroup()
	{
		return tutGroup;
	}
	public void settutGroup(String tutGroup)
	{
		this.tutGroup = tutGroup;
	}
	/*
	 *retrieve and set vacancy
	 */
	public int getVacancy()
	{
		return vacancy;
	}
	public void setVacancy(int vacancy)
	{
		this.vacancy = vacancy;
	}
	/*
	 *retrieve and set waiting list
	 */
	public int getWaitingList()
	{
		return waitingList;
	}
	public void setWaitingList(int waitingList)
	{
		this.waitingList = waitingList;
	}
	/*
	 *retrieve and set waiting queue
	 */
	public ArrayList<String> getWaitingQueue()
	{
		return waitingqueue;
	}
	public void setWaitingQueue(ArrayList<String> waitingqueue)
	{
		this.waitingqueue = waitingqueue;
	}


}
