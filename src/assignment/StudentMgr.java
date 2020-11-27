package assignment;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;



public class StudentMgr extends AllTXT {
	/*
	 *add course
	 *@param index
	 *@param student
	 *@param courseID
	 *@throws Exception
	 */
	public static void addCourse(String index, Student student,String courseID) throws Exception {
		/*
		 *check if student has already registered for a course/index
		 */
		ArrayList<String> getqueue = new ArrayList<String>();
		int checkClash = StudentMgr.checkClash(index, student, courseID);
		if (checkClash==4) { 
		for (Index i : indexlist) {
			if (i.getindexNum().equalsIgnoreCase(index)) {
				
				int vac = i.getVacancy();
				int waitlist = i.getWaitingList();
				String courseCode = i.getcourseCode1();
				String status= "WAITING";
				
				for (RegisteredCourses j: registeredCourseslist) {
					if (j.getIndexNumber().equalsIgnoreCase(index) && student.getMatricNumber().equalsIgnoreCase(j.getMatricID())) {
						System.out.println("You have already registered for this Index!");
						return;
					}
					else if (j.getCourseCode().equalsIgnoreCase(i.getcourseCode1()) && student.getMatricNumber().equals(j.getMatricID())) {
						System.out.println("You have already registered for this Course!");
						return;
					}		
				}
				/*
				 *check if student is overloading AU
				 */
				boolean maxAU = checkAU(student);
				
				if (maxAU) {
					if (i.getVacancy()==0) {
						waitlist++;
						status = "WAITING";
					}
					else if (i.getVacancy()>0) {
						vac--;
						status = "REGISTERED";
					}
					/*
					 *puts student in waiting list or confirm registration
					 */
					RegisteredCourses registerNew = new RegisteredCourses(student.getMatricNumber(),courseCode,index, status); //add course to registeredCourses
					registeredCourseslist.add(registerNew);
					saveRegisteredCourse();
				
					if (status.equalsIgnoreCase("WAITING")) {
						getqueue = i.getWaitingQueue();
						getqueue.add(student.getMatricNumber());
						Index updatedIndex = new Index(i.getcourseCode1(),i.getindexNum(),i.gettutGroup(),vac,waitlist,getqueue);
						indexlist.remove(i);
						indexlist.add(updatedIndex);
						saveIndex();
						System.out.println("You have been placed in the waiting list for Index " + index + " of Course Code " + courseCode);
						return;
					}
					else if (status.equalsIgnoreCase("REGISTERED")) {
						Index updatedIndex = new Index(i.getcourseCode1(),i.getindexNum(),i.gettutGroup(),vac,waitlist,i.getWaitingQueue());
						indexlist.remove(i);
						indexlist.add(updatedIndex);
						saveIndex();	
						System.out.println("You have been successfully registered for Index " + index + " of Course Code " + courseCode);
						return;
					}
				}
				else return;
			}
		}
		}
		else {
			return;
		}
	}
	
/**
 * drop course
 * @param courseID
 * @param student
 * @param index
 * @throws Exception
 */
	public static void dropCourse(String courseID, Student student,String index) throws Exception{
		
		int count=0;
		int count1=0;
		for (Course k: courselist) {
			count++;
			if (k.getcourseCode1().equalsIgnoreCase(courseID)) break;
			if (count==courselist.size()) {
				System.out.println("Course does not exist!");
				return;
			}
		}
		
		for (RegisteredCourses r: registeredCourseslist) {
			if (r.getCourseCode().equalsIgnoreCase(courseID) && r.getIndexNumber().equalsIgnoreCase(index)) break;
			
			count1++;
			if (count1==registeredCourseslist.size()) {
				System.out.println("You have not registered for the Course or Index!");
				return;
			}		
		}
		
		
		for (RegisteredCourses i: registeredCourseslist) {
			if(i.getCourseCode().equalsIgnoreCase(courseID) && student.getMatricNumber().equalsIgnoreCase(i.getMatricID()))
			{	
				for (Index j: indexlist) {
					if (i.getIndexNumber().equalsIgnoreCase(j.getindexNum())) {
						if (i.getRegisterStatus().equalsIgnoreCase("WAITING") && j.getWaitingList()>0) {
							int waitinglist = j.getWaitingList();
							waitinglist--;
							ArrayList<String> updatequeue = j.getWaitingQueue();
							for (String s: updatequeue) {
								if (s.equalsIgnoreCase(student.getMatricNumber())) {
									updatequeue.remove(s);
									break;
								}
							}			
							Index toUpdate = new Index(j.getcourseCode1(),j.getindexNum(),j.gettutGroup(),j.getVacancy(),waitinglist,updatequeue);		
							indexlist.remove(j);
							indexlist.add(toUpdate);
							saveIndex();
						}
						else if (i.getRegisterStatus().equalsIgnoreCase("REGISTERED")) {
							int vac = j.getVacancy();
							vac++;
							Index toUpdate = new Index(j.getcourseCode1(),j.getindexNum(),j.gettutGroup(),vac,j.getWaitingList(),j.getWaitingQueue());
							indexlist.remove(j);
							indexlist.add(toUpdate);
							saveIndex();
						}	
						registeredCourseslist.remove(i);
						saveRegisteredCourse();	
						updateWaitlist();
						System.out.println("Successfully dropped Course "+ courseID);
						return;
					}
				}
				}
		}
				 System.out.println("You have not registered for the Course or Index!");
				 return;
	}

	
	/*
	 *@param Student
	 *@throws IOExeption
	 */
	public static void printCourseReg(Student s) throws IOException, ParseException {
		int count=0;
		System.out.println("CourseID         CourseName      Index      LessonDay      LessonStartTime      LessonEndTime      LessonLocation      LessonType            ExamDate");
		System.out.println("======================================================================================================================================================");
		
		for (RegisteredCourses i: registeredCourseslist) {
			if (i.getMatricID().equalsIgnoreCase(s.getMatricNumber()) && i.getRegisterStatus().equalsIgnoreCase("REGISTERED")) {
				for (Course j: courselist) {
					if (j.getcourseCode1().equals(i.getCourseCode())) {
						for (Lesson l: lessonlist) {
							if (l.getcourseCode1().equalsIgnoreCase(j.getcourseCode1()) && l.getIndexNum().equalsIgnoreCase(i.getIndexNumber())) {						
								System.out.printf("%8s %18s %10s %14s %20s %18s %19s %15s %19s", i.getCourseCode(),j.getcourseName1(), i.getIndexNumber(), l.getLessonDay(), l.getLessonStartTimeStr(), l.getLessonEndTimeStr()
										, l.getLessonLoc(), l.getLessonType(), j.getExamDate());
								System.out.println();
								count++;
							}
						}
					}
					
				
				}
			}
		
		}
		if (count==0) System.out.println("You have not registered for any Courses!");
	}
	/*
	 *check if course has vacancies
	 */
	public static void checkVacancies(String index,String courseID) throws IOException, ParseException {
		int count=0;
		
		for (Course c: courselist) {
			if (c.getcourseCode1().equalsIgnoreCase(courseID)) break;
			
			count++;
			if (count==courselist.size()) {
				System.out.println("Course Code does not exist!");
				return;
			}
		}
		
		
		for (Index i: indexlist) {
			if (i.getindexNum().equalsIgnoreCase(index) && i.getcourseCode1().equalsIgnoreCase(courseID)) {
				for (Course j: courselist) {
					if (j.getcourseCode1().equalsIgnoreCase(i.getcourseCode1())) {
						System.out.println("Vacancies for Course Code "+j.getcourseCode1() + ", Index "+ i.getindexNum() + ": " + i.getVacancy() );
						return;
					}
				}
				
			}
		}	
		System.out.println("Course "+courseID+" does not have the Index "+index);
		return;
	}
	
	
	/*
	 *check for clash
	 */
	public static int checkClash(String indexNum,Student s,String courseID) throws ParseException, IOException {
		int count1=0;
		int count2=0;
		String courseno="";
		String tutorialgrp="";
		int vac=0;
		int wait=0;
		ArrayList<Lesson> lessonsameindex = new ArrayList<Lesson>();		
		for (Course a: courselist) {
			if (a.getcourseCode1().equalsIgnoreCase(courseID)){ //verify course id+index exist
				break;
			}
			count2++;
			if (count2==courselist.size()) {
				System.out.println("No such Course!");
				return 1; // 1 is no such course
			}
		}
		
		for (Index q: indexlist) {
			if (q.getcourseCode1().equalsIgnoreCase(courseID) && q.getindexNum().equalsIgnoreCase(indexNum)){ //verify course id+index exist
				tutorialgrp = q.gettutGroup();
				vac = q.getVacancy();
				wait = q.getWaitingList();
				break;
			}
			count1++;
			if (count1==indexlist.size()) {
				System.out.println("No such Index for this Course!");
				return 0; //0 for no such index
			}
		}
		/*
		 *get all lessons for that particular index
		 */
		for (Lesson p: lessonlist) { 
			if (p.getIndexNum().equalsIgnoreCase(indexNum)) {
				Lesson toadd = new Lesson(p.getcourseCode1(),p.getIndexNum(),p.getLessonDay(),p.getLessonStartTime(),p.getLessonEndTime(),p.getLessonType(),p.getLessonLoc());
				lessonsameindex.add(toadd);
			}
		}	
		/*
		 *get course details
		 */
		for (Course i: courselist) { 
			if (i.getcourseCode1().equalsIgnoreCase(courseID)) {
				Course toBeChecked = new Course(i.getcourseCode1(),i.getcourseName1(),i.getAU(),i.getschool1()
						,i.getcourseType1(),i.getExamDateCal());
				
				for (RegisteredCourses j: registeredCourseslist) { //check if examdate clash with existing registered courses
					if (j.getMatricID().equals(s.getMatricNumber())&& !j.getCourseCode().equalsIgnoreCase(courseID)) { 
						for (Course h: courselist) {
							if (h.getcourseCode1().equals(j.getCourseCode())) {
								Calendar registeredTime = h.getExamDateCal();
								
								if (registeredTime.equals(toBeChecked.getExamDateCal())) {
									System.out.println("Course "+ courseID + " exam date clashes with your registered Course "+ j.getCourseCode());
									return 3; //3 is for clash of examdates
								}
							}
						} 
						for (Lesson l: lessonlist) {
							if (j.getIndexNumber().equalsIgnoreCase(l.getIndexNum())) {
								for (Lesson x: lessonsameindex) {
									if (!l.getLessonDay().equalsIgnoreCase(x.getLessonDay())) {
										continue;
									}
									else if (l.getLessonDay().equalsIgnoreCase(x.getLessonDay())) {	
										if (l.getLessonStartTime().equals(x.getLessonStartTime()) || l.getLessonStartTime().before(x.getLessonStartTime())&&x.getLessonStartTime().before(l.getLessonEndTime()) ||
												x.getLessonStartTime().before(l.getLessonStartTime())&&l.getLessonStartTime().before(x.getLessonEndTime())) {
										System.out.println("Lesson time for Course Code "+ courseID + " Index "+ indexNum + " clashes with Registered Index "+ 
												j.getIndexNumber()+ " for Course Code "+ j.getCourseCode());
										return 2; //2 is for clash of lessons
									}	
								}
								}			
							}
						}
					}
		}		 		
		}
		}
		/*
		 *no clash
		 */
		return 4; 
		
		
		
		
	}
	
	
	public static void changeCourseIndex(Student student, String currentIndex, String newIndex,String courseID) throws Exception {
		/*
		 *for old index
		 */
		Index updateindex = new Index();
		/*
		 *for new index
		 */
		Index updateindex1 = new Index(); 
		int count=0;
		int count1=0;
		
		for (Index l: indexlist) {
			if (l.getindexNum().equalsIgnoreCase(currentIndex) && l.getcourseCode1().equalsIgnoreCase(courseID)) {
				updateindex.setcourseCode1(l.getcourseCode1());
				updateindex.setindexNum(l.getindexNum());
				updateindex.settutGroup(l.gettutGroup());
				updateindex.setVacancy(l.getVacancy());
				updateindex.setWaitingList(l.getWaitingList());
				updateindex.setWaitingQueue(l.getWaitingQueue());
				count++;
			}
			if (l.getindexNum().equalsIgnoreCase(newIndex)&& l.getcourseCode1().equalsIgnoreCase(courseID)) {
				updateindex1.setcourseCode1(l.getcourseCode1());
				updateindex1.setindexNum(l.getindexNum());
				updateindex1.settutGroup(l.gettutGroup());
				updateindex1.setVacancy(l.getVacancy());
				updateindex1.setWaitingList(l.getWaitingList());
				updateindex1.setWaitingQueue(l.getWaitingQueue());	
				count++;
			}
			count1++;
			if (count==2) break;
			else if (count1== indexlist.size()) {
				System.out.println("Invalid Entry! Check that you have input the correct Course Code & Indexes");
				return;
			}
		}
		
		for (RegisteredCourses i: registeredCourseslist) {
			if (i.getMatricID().equalsIgnoreCase(student.getMatricNumber()) && i.getRegisterStatus().equalsIgnoreCase("REGISTERED")&& i.getIndexNumber().equalsIgnoreCase(currentIndex)) {
				int check = checkClash(newIndex,student,courseID);
				if (check==4) {
					for (Index j: indexlist) {
						if (j.getindexNum().equalsIgnoreCase(newIndex) && j.getVacancy()>0) { //only updated if new index has vacancy
							/*
							 *update old index
							 */
							int vac = updateindex.getVacancy();
							vac++;
							Index updatedindex = new Index(updateindex.getcourseCode1(),updateindex.getindexNum(),updateindex.gettutGroup(),vac,updateindex.getWaitingList(),updateindex.getWaitingQueue());
					
							/*
							 *update new index
							 */
							int vac1 = updateindex1.getVacancy();
							vac1--;
							Index updatedindex1 = new Index(updateindex1.getcourseCode1(),updateindex1.getindexNum(),updateindex1.gettutGroup(),vac1,updateindex1.getWaitingList(),updateindex1.getWaitingQueue());
							
							for (Index x: indexlist) {
								if (x.getindexNum().equalsIgnoreCase(currentIndex)) {
									indexlist.remove(x);
									indexlist.add(updatedindex);
									saveIndex();
									break;
								}
							}
							for (Index w: indexlist) {
								if (w.getindexNum().equalsIgnoreCase(newIndex)) {
									indexlist.remove(w);
									indexlist.add(updatedindex1);
									saveIndex();
									break;
								}
							}
							
							
							registeredCourseslist.remove(i);
							RegisteredCourses updatereg = new RegisteredCourses(student.getMatricNumber(),i.getCourseCode(),newIndex,"REGISTERED");
							registeredCourseslist.add(updatereg);
							saveRegisteredCourse();
							updateWaitlist();
							System.out.println("Successfully changed from Index "+currentIndex+" to Index "+newIndex+" for Course Code "+courseID);
							return;
						}
						else if (j.getindexNum().equalsIgnoreCase(newIndex) && j.getVacancy()==0) {
							System.out.println("Index "+ newIndex+" currently has no vacancies!");
							return;
						}
					}
					
					
					
				}
				else {
					System.out.println("Your request has been denied!");
					return;
				}
			}
			else if (i.getMatricID().equalsIgnoreCase(student.getMatricNumber()) && i.getRegisterStatus().equalsIgnoreCase("WAITING") && i.getIndexNumber().equalsIgnoreCase(currentIndex)) {
				System.out.println("You are under the waiting list for Index "+currentIndex+". \n"
						+ "If you wish to change to another Index please drop the existing Index and add New Index!");
				return;
			}
		}
		System.out.println("You are not registered for this Index or Course!");
		return;
	}
	
	public static void updateWaitlist() throws Exception {

		for (Index i: indexlist) {
			if (i.getWaitingList()>0 && i.getVacancy()>0) {
				String getID = i.getWaitingQueue().get(0);
				int vac = i.getVacancy();
				vac--;
				int waitlist = i.getWaitingList();
				waitlist--;
				i.getWaitingQueue().remove(0);
				Index newentry = new Index(i.getcourseCode1(),i.getindexNum(),i.gettutGroup(),vac,waitlist,i.getWaitingQueue());
				indexlist.remove(i);
				indexlist.add(newentry);
				saveIndex();
				
				for (RegisteredCourses r: registeredCourseslist) {
					if (r.getMatricID().equalsIgnoreCase(getID) && r.getIndexNumber().equalsIgnoreCase(i.getindexNum()) && r.getRegisterStatus().equalsIgnoreCase("WAITING")) {
						for (Student n: studentlist) {
							if (n.getMatricNumber().equalsIgnoreCase(r.getMatricID())) {
								NotificationMgr.sendEmail(n,r.getCourseCode());
								break;
							}
						} 
						RegisteredCourses newentry1 = new RegisteredCourses(r.getMatricID(),r.getCourseCode(),r.getIndexNumber(),"REGISTERED");
						registeredCourseslist.remove(r);
						registeredCourseslist.add(newentry1);
						saveRegisteredCourse();
						break;
						
					}
					
				}
				break;
			}
		}	
	}
	
	public static boolean checkAU(Student s) {
		int totalAU=0;
		for (RegisteredCourses i: registeredCourseslist) {
			if (i.getMatricID().equalsIgnoreCase(s.getMatricNumber()) && i.getRegisterStatus().equalsIgnoreCase("REGISTERED")) {
				for (Course j: courselist) {
					if (j.getcourseCode1().equalsIgnoreCase(i.getCourseCode())) {
						totalAU += j.getAU();
						break;
				}
			}
		}		
	}
		if (totalAU>=18) {
			System.out.println("You have reached the Maximum number of AUs that you can register for this semester!");
			return false;
		}
		return true;		
		}
	
	public static void swapStudent(Student s1, String student2, String index,String index2, String courseID) throws Exception {
		int count0=0;
		int count=0;
		int count1=0;
		int check1=0;
		int check2=0;
		Student s2 = new Student();
		
		for (Student s: studentlist) {
			if (s.getMatricNumber().equalsIgnoreCase(student2)) {
				s2.setName(s.getName());
				s2.setMatricNumber(s.getMatricNumber());
				s2.setUsername(s.getUsername());
				s2.setGender(s.getGender());
				s2.setEmail(s.getEmail());
				s2.setPassword(s.getPassword());
				s2.setAccessEnd(s.getAccessEndCal());
				s2.setAccessStart(s.getAccessStartCal());
				s2.setNationality(s.getNationality());
			}
		}
		
		if (s1.getMatricNumber().equalsIgnoreCase(s2.getMatricNumber())) {
			System.out.println("You cannot change an Index with yourself!");
			return;
		}
		/*
		 *check if 2nd student exists
		 */
		for (Student l: studentlist) { 
			
			if (l.getMatricNumber().equalsIgnoreCase(s2.getMatricNumber())) break;
			
			count1++;
			if (count1==studentlist.size()) {
				System.out.println("The Student ID does not exist, Please Try Again");
				return;
			}
		}
		
		/*
		 *check coursecode and index validity
		 */
		for (Index i: indexlist) { 
			if (i.getcourseCode1().equalsIgnoreCase(courseID) && i.getindexNum().equalsIgnoreCase(index)) break;	
			count++;
			if (count==indexlist.size()) {
				System.out.println("Either the Course Code does not exist or the Index does not belong to the Course Code. Please Try Again");
				return;
			}
		}
		
		for (Index i: indexlist) { 
			if (i.getcourseCode1().equalsIgnoreCase(courseID) && i.getindexNum().equalsIgnoreCase(index2)) break;
			
			count0++;
			if (count0==indexlist.size()) {
				System.out.println("Either the Course Code does not exist or the Index does not belong to the Course Code. Please Try Again");
				return;
			}
		}
		/*
		 *check if both are registered for the same index
		 */
		for (RegisteredCourses j: registeredCourseslist) { 
			if (j.getMatricID().equalsIgnoreCase(s1.getMatricNumber()) && j.getRegisterStatus().equalsIgnoreCase("REGISTERED") && j.getIndexNumber().equalsIgnoreCase(index)) {
				check1++;			
			}
			if (j.getMatricID().equalsIgnoreCase(s1.getMatricNumber()) && j.getRegisterStatus().equalsIgnoreCase("REGISTERED") && j.getIndexNumber().equalsIgnoreCase(index)) {
				check2++;			
			}

		}
		if (check1==1 && check2!=1) {
			System.out.println("Index "+ index+" is not registered for Student "+ s2.getMatricNumber() +" , "+ s2.getName());
			return;
		}
		else if (check1!=1 && check2==1) {
			System.out.println("Index "+ index+" is not registered for Student "+ s1.getMatricNumber() +" , "+ s1.getName());
			return;
		}
		else if (check1==0 && check2==0) {
			System.out.println("Check that you or the other Student has registered for the Index "+ index);
			return;
		}
		
		for (RegisteredCourses r: registeredCourseslist) {
			if (r.getMatricID().equalsIgnoreCase(s1.getMatricNumber()) && r.getRegisterStatus().equalsIgnoreCase("REGISTERED") && r.getIndexNumber().equalsIgnoreCase(index)) {
				int checkstud1 = checkClash(index2,s1,courseID);
				
				if (checkstud1!=4) return;
				
				else if (checkstud1==4) {
					for (RegisteredCourses c: registeredCourseslist) {
						if (c.getMatricID().equalsIgnoreCase(s2.getMatricNumber()) && c.getRegisterStatus().equalsIgnoreCase("REGISTERED") && c.getIndexNumber().equalsIgnoreCase(index2)) {
							int checkstud2 = checkClash(index,s2,courseID);
							if (checkstud2!=4) return;
					
							else if (checkstud2==4) {
								RegisteredCourses stud1 = new RegisteredCourses(s1.getMatricNumber(),r.getCourseCode(),index2,"REGISTERED");
								RegisteredCourses stud2 = new RegisteredCourses(s2.getMatricNumber(),r.getCourseCode(),index,"REGISTERED");
								registeredCourseslist.remove(r);
								registeredCourseslist.remove(c);
								registeredCourseslist.add(stud1);
								registeredCourseslist.add(stud2);
								saveRegisteredCourse();
								
								System.out.println("You have successfully swapped Index "+ index +" to Index "+index2+" with Student "+s2.getMatricNumber()+" for Course "+courseID);
								return;
					}
					
				}
			}
			
		}
		
		}
	}	
}
	
	public static void changePassword(String username) throws Exception
	{ 
		boolean flag=false;
		Scanner sc=new Scanner (System.in);
		System.out.println("RECONFIRMATION");
		System.out.println("================");
		System.out.println("Enter your old password");
		String pass=sc.next();
		for(int i=0;i<studentlist.size();i++)
		{
			if(username.equalsIgnoreCase(studentlist.get(i).getUsername()))
			{ Generate g1=new Generate();
			  flag=true;
				double password=g1.hashing(pass);
				if(password==studentlist.get(i).getPassword())
				{
					Generate g2=new Generate();
					System.out.println("Enter your new password:");
					String passNew=sc.next();
					double newHash=g2.hashing(passNew);
					studentlist.get(i).setPassword(newHash);
					saveStudent();
					break;
				}
			}
		}
		if(flag==false)   
			System.out.println("Incorrect password!");
}
}
