package com.app.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.jdbc.support.rowset.SqlRowSet;

/**
 * Course model used in CRUD and displays detailed information about an available course to join as a student.
 * @author Matt & Joey
 *
 */
public class Course 
{
	@NotNull(message="ID cannot be null.")
	@Size(min=4, max=30, message="ID must be between 5 and 30 characters.")
	private String courseID;
	@NotNull(message="Title cannot be null.")
	@Size(min=5, max=200, message="Title must be between 5 and 200 characters.")
	private String title;
	@NotNull(message="description cannot be null.")
	@Size(min=5, max=200, message="description must be between 5 and 200 characters.")
	private String description;
	@NotNull(message="Major cannot be null.")
	@Size(min=5, max=200, message="Major must be between 5 and 200 characters.")
	private String major;
	@NotNull(message="Class Location cannot be null.")
	@Size(min=5, max=200, message="Class Location must be between 5 and 200 characters.")
	private String classLocation;
	@NotNull(message="Class Times cannot be null.")
	@Size(min=5, max=200, message="Class Times must be between 5 and 200 characters.")
	private String classTimes;
	@NotNull(message="Tutor Times cannot be null.")
	@Size(min=5, max=200, message="Tutor Times must be between 5 and 200 characters.")
	private String tutorTimes;
	
	private List<User> instructors;
	private List<User> tutors;	
	private List<User> students;
	
	@Size(max=500, message="Image URL length cannot be longer than 500 characters.")
	private String image;

	public Course() 
	{
		courseID = "";
		title = "";
		description = "";
		major = "";
		classLocation = "";
		classTimes = "";
		tutorTimes = "";
		instructors = new ArrayList<User>();
		tutors = new ArrayList<User>();
		students = new ArrayList<User>();
		image = null;
	}

	public Course(String courseID, String title, String description, String major, String classLocation, String classTimes,
			String tutorTimes, List<User> instructors, List<User> tutors, List<User> students, String image) {
		super();
		this.courseID = courseID;
		this.title = title;
		this.description = description;
		this.major = major;
		this.classLocation = classLocation;
		this.classTimes = classTimes;
		this.tutorTimes = tutorTimes;
		this.instructors = instructors;
		this.tutors = tutors;
		this.students = students;
		this.image = image;
	}

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getClassLocation() {
		return classLocation;
	}

	public void setClassLocation(String classLocation) {
		this.classLocation = classLocation;
	}

	public String getClassTimes() {
		return classTimes;
	}

	public void setClassTimes(String classTimes) {
		this.classTimes = classTimes;
	}

	public String getTutorTimes() {
		return tutorTimes;
	}

	public void setTutorTimes(String tutorTimes) {
		this.tutorTimes = tutorTimes;
	}

	public List<User> getInstructors() {
		return instructors;
	}

	public void setInstructors(List<User> instructors) {
		this.instructors = instructors;
	}

	public List<User> getTutors() {
		return tutors;
	}

	public void setTutors(List<User> tutors) {
		this.tutors = tutors;
	}

	public List<User> getStudents() {
		return students;
	}

	public void setStudents(List<User> students) {
		this.students = students;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Course [courseID =" + courseID + ", title=" + title + ", description=" + description + ", major=" + major
				+ ", classLocation=" + classLocation + ", classTimes=" + classTimes + ", tutorTimes=" + tutorTimes
				+ ", instructors=" + instructors + ", tutors=" + tutors + ", students=" + students + ", image=" + image
				+ "]";
	}

	public static Course getSqlRowSet(SqlRowSet srs)
	{
		Course course = new Course(
				srs.getString("COURSE_ID"),
				srs.getString("TITLE"),
				srs.getString("DESCRIPTION"),
				srs.getString("MAJOR"),
				srs.getString("CLASS_LOCATION"),
				srs.getString("CLASS_TIMES"),
				srs.getString("TUTOR_TIMES"),
				null,
				null,
				null,
				srs.getString("IMAGE")
				);
				
		return course;
	}
	
	public static String getSqlParams()
	{
		return 	  "COURSE_ID, "
				+ "TITLE, "
				+ "DESCRIPTION, "
				+ "MAJOR, "
				+ "CLASS_LOCATION, "
				+ "CLASS_TIMES, "
				+ "TUTOR_TIMES, "
				+ "IMAGE";
	}
	
	public static String getSqlValues(Course course)
	{
		return  "'" + course.getCourseID() + "', " +
				"'" + course.getTitle() + "', " +
				"'" + course.getDescription() + "', " +
				"'" + course.getMajor() + "', " +
				"'" + course.getClassLocation() + "', " +
				"'" + course.getClassTimes() + "', " +
				"'" + course.getTutorTimes() + "', " +
				"'" + course.getImage() + "'";
	}
}