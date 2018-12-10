package com.app.business;

import java.util.List;

import com.app.exceptions.CourseErrorException;
import com.app.exceptions.CourseFoundException;
import com.app.exceptions.CourseNotFoundException;
import com.app.model.Course;

public interface CourseBusinessInterface 
{
	/**
	 * Forwards the request to the course persistence layer.
	 * Gets a single course by either selection or search
	 * 
	 * @param Course course
	 * @return Course
	 * @throws CourseNotFoundException
	 */
	public Course getOneCourse(Course course) throws CourseNotFoundException;
	
	/**
	 * Forwards the requests to the course persistence layer.
	 * Returns course results based on search parameters.
	 * 
	 * @param Course course
	 * @return List<Course>
	 * @throws CourseErrorException
	 */
	public List<Course> getSearchedCourses(Course course) throws CourseErrorException;
	
	/**
	 * Forwards the request to the course persistence layer.
	 * Returns all courses at a limit.
	 * 
	 * @return List<Course>
	 * @throws CourseErrorException
	 */
	public List<Course> getAllCourses() throws CourseErrorException;
	
	/**
	 * Forwards the request to the course persistence layer.
	 * Adds a new course by form input
	 * 
	 * @param Course course
	 * @return boolean
	 * @throws CourseFoundException
	 * @throws CourseErrorException
	 */
	public boolean addCourse(Course course) throws CourseFoundException, CourseErrorException;
	
	/**
	 * Forwards the request to the course persistence layer.
	 * Updates changes to the selected course.
	 * 
	 * @param Course course
	 * @return boolean
	 * @throws CourseNotFoundException
	 * @throws CourseErrorException
	 */
	public boolean modifyCourse(Course course) throws CourseNotFoundException, CourseErrorException;
	
	/**
	 * Forwards the request to the course persistence layer.
	 * Removes the selected course.
	 * 
	 * @param Course course
	 * @return boolean
	 * @throws CourseNotFoundException
	 * @throws CourseErrorException
	 */
	public boolean removeCourse(Course course) throws CourseNotFoundException, CourseErrorException;
}
