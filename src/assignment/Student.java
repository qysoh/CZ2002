package assignment;
import java.util.*;

public class Student {
	/*
	 *attributes of student class
	 */
	private String name;
	private String username;
	private double password;
	private char gender;
	private String nationality;
	private String email;
	private String matricID;
	private Calendar accessStart;
	private Calendar accessEnd;
	
	public Student() {
	}
	/*
	 *constructor for student class
	 *@param matricID
	 *@param username
	 *@param name
	 *@param gender
	 *@param nationality
	 *@param email
	 *@param password
	 *@param accessStart
	 *@param accessEnd
	 */
	public Student(String name, String username, double password, char gender, String nationality,String email, String matricID, Calendar accessStart, Calendar accessEnd) 
	{
		this.matricID = matricID; 
		this.username = username;
		this.name = name;
		this.gender = gender;
		this.nationality=nationality;
		this.email = email;
		this.password = password;
		this.accessStart = accessStart;
		this.accessEnd = accessEnd;
	}
	/*
	 *set and retrieve username
	 */
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	/*
	 *set and retrieve name
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	/*
	 *set and retrieve matric ID
	 */
	public String getMatricNumber() {
		return matricID;
	}

	public void setMatricNumber(String matricID) {
		this.matricID = matricID;
	}
	/*
	 *set and retrieve gender
	 */
	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}
	/*
	 *set and retrieve nationality
	 */
	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	/*
	 *set and retrieve email
	 */
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	/*
	 *set and retrieve password
	 */
	public double getPassword() {
		return password;
	}
	
	public void setPassword(double password) {
		this.password = password;
	}
	/*
	 *set and retrieve access start date
	 */
	public String getAccessStart() {
		return CalendarMgr.dateTimeStr(accessStart);
	}
	
	public Calendar getAccessStartCal() {
		return accessStart;
	}
	
	public void setAccessStart(Calendar accessStart) {
		this.accessStart = accessStart;
	}
	/*
	 *set and retrieve access end date
	 */
	public String getAccessEnd() {
		return CalendarMgr.dateTimeStr(accessEnd);
	}
	
	public Calendar getAccessEndCal() {
		return accessEnd;
	}
	
	public void setAccessEnd(Calendar accessEnd) {
		this.accessEnd = accessEnd;
	}
}
