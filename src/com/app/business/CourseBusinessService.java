package com.app.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.data.DataAccessInterface;
import com.app.exceptions.CourseErrorException;
import com.app.exceptions.CourseFoundException;
import com.app.exceptions.CourseNotFoundException;
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
	 * @throws CourseNotFoundException
	 * @param Course course
	 * @return Course
	 */
	public Course findBy(Course course) throws CourseNotFoundException
	{
		// Returns results from CourseDataService.findBy(course)
		course = courseDAO.findBy(course);
		if(course == null)
		{
			throw new CourseNotFoundException();
		}
		
		return course;
	}
	
	/**
	 * Forwards the requests to the course persistence layer
	 * 
	 * @throws CourseErrorException
	 * @param Course course
	 * @return List<Course>
	 */
	public List<Course> findAll(Course course) throws CourseErrorException
	{
		// Returns results from CourseDataService.findAll(course)
		List<Course> courses = courseDAO.findAll(course);
		if(courses == null)
		{
			throw new CourseErrorException();
		}
		
		return courses;
	}
	
	/**
	 * Forwards the request to the course persistence layer.
	 * 
	 * @throws CourseErrorException
	 * @return List<Course>
	 */
	public List<Course> findAll() throws CourseErrorException
	{
		// Returns results from CourseDataService.findAll()
		List<Course> courses = courseDAO.findAll();
		if(courses == null)
		{
			throw new CourseErrorException();
		}
		
		return courses;
	}
	
	/**
	 * Forwards the request to the course persistence layer
	 * 
	 * @throws CourseFoundException, CourseErrorException
	 * @param Course course
	 * @return boolean
	 */
	public boolean createCourse(Course course) throws CourseFoundException, CourseErrorException 
	{
		Course exists = courseDAO.findBy(course);
		if(exists != null)
		{
			throw new CourseFoundException();
		}
		
		// Return results from CourseDataService.create(course)
		boolean result = courseDAO.create(course);
		if(result == false)
		{
			throw new CourseErrorException();
		}
		
		return result;
	}
}
