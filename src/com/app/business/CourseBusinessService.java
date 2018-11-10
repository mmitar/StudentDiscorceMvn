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
	 * Gets a single course by either selection or search
	 * 
	 * @param Course course
	 * @return Course
	 * @throws CourseNotFoundException
	 */
	public Course getOneCourse(Course course) throws CourseNotFoundException
	{
		// Returns results from CourseDataService.findBy(course)
		Course exists = courseDAO.findBy(course);
		if(exists == null)
		{
			throw new CourseNotFoundException();
		}
		
		return exists;
	}
	
	/**
	 * Forwards the requests to the course persistence layer.
	 * Returns course results based on search parameters.
	 * 
	 * @param Course course
	 * @return List<Course>
	 * @throws CourseErrorException
	 */
	public List<Course> getSearchedCourses(Course course) throws CourseErrorException
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
	 * Returns all courses at a limit.
	 * 
	 * @return List<Course>
	 * @throws CourseErrorException
	 */
	public List<Course> getAllCourses() throws CourseErrorException
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
	 * Forwards the request to the course persistence layer.
	 * Adds a new course by form input
	 * 
	 * @param Course course
	 * @return boolean
	 * @throws CourseFoundException
	 * @throws CourseErrorException
	 */
	public boolean addCourse(Course course) throws CourseFoundException, CourseErrorException 
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
	
	/**
	 * Forwards the request to the course persistence layer.
	 * Updates changes to the selected course.
	 * 
	 * @param Course course
	 * @return boolean
	 * @throws CourseNotFoundException
	 * @throws CourseErrorException
	 */
	public boolean modifyCourse(Course course) throws CourseNotFoundException, CourseErrorException
	{
		Course exists = courseDAO.findBy(course);
		if(exists == null)
		{
			throw new CourseNotFoundException();
		}
		
		boolean result = courseDAO.update(course);
		if(result == false)
		{
			throw new CourseErrorException();
		}
		
		return result;
	}
	
	/**
	 * Forwards the request to the course persistence layer.
	 * Removes the selected course.
	 * 
	 * @param course
	 * @return
	 * @throws CourseNotFoundException
	 * @throws CourseErrorException
	 */
	public boolean removeCourse(Course course) throws CourseNotFoundException, CourseErrorException
	{	
		Course exists = courseDAO.findBy(course);
		if(exists == null)
		{
			throw new CourseNotFoundException();
		}
		
		boolean result = courseDAO.delete(course);
		if(result == false)
		{
			throw new CourseErrorException();
		}
		
		return result;
	}
}
