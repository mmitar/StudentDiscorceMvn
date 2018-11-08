package com.app.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.data.DataAccessInterface;
import com.app.model.Course;

public class CourseBusinessService implements CourseBusinessInterface
{
	/**
	 * Dependency Injected
	 */
	@Autowired
	private DataAccessInterface<Course> courseDAO;
	
	/**
	 * Default Constructor
	 */
	public CourseBusinessService() {}
	
	/**
	 * Forwards the request to the course persistence layer.
	 * 
	 * @param Course course
	 * @return Course
	 */
	public Course findBy(Course course)
	{
		// Returns results from CourseDataService.findBy(course)
		return this.courseDAO.findBy(course);
	}
	
	/**
	 * Forwards the requests to the course persistence layer
	 * 
	 * @param Course course
	 * @return List<Course>
	 */
	public List<Course> findAll(Course course)
	{
		// Returns results from CourseDataService.findAll(course)
		return this.courseDAO.findAll(course);
	}
	
	/**
	 * Forwards the request to the course persistence layer.
	 * 
	 * @return List<Course>
	 */
	public List<Course> findAll()
	{
		// Returns results from CourseDataService.findAll()
		return this.courseDAO.findAll();
	}
	
	/**
	 * Forwards the request to the course persistence layer
	 * 
	 * @param Course course
	 * @return boolean
	 */
	public boolean createCourse(Course course) 
	{
		// Return results from CourseDataService.create(course)
		return this.courseDAO.create(course);
	}
}
