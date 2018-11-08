package com.app.business;

import java.util.List;

import com.app.model.Course;

public interface CourseBusinessInterface 
{
	/**
	 * Forwards the request to the course persistence layer.
	 * 
	 * @param Course course
	 * @return Course
	 */
	public Course findBy(Course course);
	
	/**
	 * Forwards the requests to the course persistence layer
	 * 
	 * @param Course course
	 * @return List<Course>
	 */
	public List<Course> findAll(Course course);
	
	/**
	 * Forwards the request to the course persistence layer.
	 * 
	 * @return List<Course>
	 */
	public List<Course> findAll();
	
	/**
	 * Forwards the request to the course persistence layer
	 * 
	 * @param Course course
	 * @return boolean
	 */
	public boolean createCourse(Course course);
}
