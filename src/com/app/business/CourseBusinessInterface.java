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
	 * 
	 * @throws CourseNotFoundException
	 * @param Course course
	 * @return Course
	 */
	public Course findBy(Course course) throws CourseNotFoundException;
	
	/**
	 * Forwards the requests to the course persistence layer
	 * 
	 * @throws CourseErrorException
	 * @param Course course
	 * @return List<Course>
	 */
	public List<Course> findAll(Course course) throws CourseErrorException;
	
	/**
	 * Forwards the request to the course persistence layer.
	 * 
	 * @throws CourseErrorException
	 * @return List<Course>
	 */
	public List<Course> findAll() throws CourseErrorException;
	
	/**
	 * Forwards the request to the course persistence layer
	 * 
	 * @throws CourseFoundException, CourseErrorException
	 * @param Course course
	 * @return boolean
	 */
	public boolean createCourse(Course course) throws CourseFoundException, CourseErrorException;
}
