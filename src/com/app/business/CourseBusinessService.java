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
			// If course does not exist, throw exception
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
		// Returns results from CourseDataService.findAll(course).
		List<Course> courses = courseDAO.findAll(course);
		if(courses == null)
		{
			// If error compiling courses, throw exception
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
		// Returns results from CourseDataService.findAll().
		List<Course> courses = courseDAO.findAll();
		if(courses == null)
		{
			// If error compiling courses, throw exception
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
		// Call DAO to see if course already exists
		Course exists = courseDAO.findBy(course);
		if(exists != null)
		{
			// If course exists, throw exception
			throw new CourseFoundException();
		}
		
		// Call DAO to add course
		boolean result = courseDAO.create(course);
		if(result == false)
		{
			// If error creating user, throw exception
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
		// Call DAO to find the course selected
		Course exists = courseDAO.findBy(course);
		if(exists == null)
		{
			// If course does not exist, throw exception
			throw new CourseNotFoundException();
		}
		
		// Call DAO to make the updates to the course
		boolean result = courseDAO.update(course);
		if(result == false)
		{
			// If error updating course, throw exception
			throw new CourseErrorException();
		}
		
		return result;
	}
	
	/**
	 * Forwards the request to the course persistence layer.
	 * Removes the selected course.
	 * 
	 * @param course
	 * @return boolean
	 * @throws CourseNotFoundException
	 * @throws CourseErrorException
	 */
	public boolean removeCourse(Course course) throws CourseNotFoundException, CourseErrorException
	{	
		// Call DAO to find the course selected
		Course exists = courseDAO.findBy(course);
		if(exists == null)
		{
			// If course does not exist, throw exception
			throw new CourseNotFoundException();
		}
		
		// Call DAO to delete the selected course
		boolean result = courseDAO.delete(course);
		if(result == false)
		{
			// If error deleting course, throw exception
			throw new CourseErrorException();
		}
		
		return result;
	}
}
