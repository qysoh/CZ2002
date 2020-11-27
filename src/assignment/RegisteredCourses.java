package assignment;

public class RegisteredCourses {
	/*
	 *attributes of registeredcourses class
	 */
	private String matricID;
	private String courseCode;
	private String index;
	private String status;
	
	/*
	 *constructor for registeredcourses class
	 * @param matric
	 * @param courseCode
	 * @param index
	 * @param status
	 */
	public RegisteredCourses(String matric, String courseCode, String index, String status) {
	
		this.matricID = matric;
		this.courseCode = courseCode;
		this.index = index;
		this.status = status;
	}
	/*
	 *retrieve and set matric ID
	 */
	public String getMatricID() {
		return matricID;
	}

	public void setMatricID(String matric) {
		this.matricID = matric ;
	}
	/*
	 *retrieve and set course code
	 */
	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	/*
	 *retrieve and set index number
	 */
	public String getIndexNumber() {
		return index;
	}

	public void setIndexNumber(String index)
	{
		this.index = index;
	}
	/*
	 *retrieve and set register status
	 */
	public String getRegisterStatus() {
		return status;
	}
	
	public void setRegisterStatus(String status) {
		this.status = status;
	}

	
}