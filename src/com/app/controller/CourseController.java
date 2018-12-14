package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.app.business.CourseBusinessInterface;
import com.app.exceptions.CourseErrorException;
import com.app.exceptions.CourseFoundException;
import com.app.exceptions.CourseNotFoundException;
import com.app.model.Course;

/**
 * Controller for handling course requests and delegating view building regarding courses
 */
@Controller
@RequestMapping("/course")
public class CourseController
{
	/**
	 * Dependency Injected
	 */
	@Autowired
	private CourseBusinessInterface courseService;
	
	/**
	 * Navs the user to the courseList page via URI. Currently no model is currently implemented
	 * but one eventually be made further in the project.
	 *
	 * @return ModelAndView courseList
	 */
	@RequestMapping(path="/new", method=RequestMethod.GET)
	public ModelAndView displayForm()
	{
		try
		{
			// Navigate a new MAV of course List
			ModelAndView mv = new ModelAndView("course/courseList");
			mv.addObject("courses", courseService.getAllCourses());
			return mv;
		}
		// If there was a system side issue
		catch(CourseErrorException e)
		{
			// Nav the user to the login screen if there is a system error
			ModelAndView mv = new ModelAndView("user/userLogin");
			mv.addObject("error", "Internal System Error. Returning user to login.");
			return mv;
		}
	}
	
	/**
	 * Collects from a form post the course Id and get the course information and passes
	 * it to the course view where all the data is populated.
	 * 
	 * @param Course course
	 * @param BindingResult validate
	 * @return ModelAndView
	 * @throws CourseErrorException
	 */
	@RequestMapping(path="/courseView", method=RequestMethod.POST)
	public ModelAndView displayCourse(@Valid @ModelAttribute("course")Course course, BindingResult validate) throws CourseErrorException{

		// Validate the selected course in case of manipulation
		if(validate.hasErrors())
		{
			ModelAndView mv = new ModelAndView("course/courseView", "course", course);
			mv.addObject("courses", courseService.getAllCourses());
			return mv;
		}
		
		try
		{
			// Connects to the CourseBusinessService to get Course by ID
			course = courseService.getOneCourse(course);
			
			// Return MAV of 
			ModelAndView mv = new ModelAndView("course/courseView");
			mv.addObject("courses", courseService.getAllCourses());
			mv.addObject("course", course);
			return mv;
		}
		// When the selected course is not actually in the system
		catch(CourseNotFoundException e)
		{
			ModelAndView mv = new ModelAndView("dashboard");
			mv.addObject("courses", courseService.getAllCourses());
			mv.addObject("error", "Course Does Not Exist. Sorry!");
			return mv;
		}
		// If there is a system issue. Logout the user.
		catch(CourseErrorException e)
		{
			ModelAndView mv = new ModelAndView("user/userLogin");
			mv.addObject("error", "Internal System Error. Returning user to login.");
			return mv;
		}
	}

	/**
	 * Checks the validation of course in the addCourse form. 
	 * Navs to the course view page if the model is valid and original
	 * 
	 * @param Course course
	 * @param BindingResult validate
	 * @return ModelAndView
	 * @throws CourseErrorException
	 */
	@RequestMapping(path="/addedCourse", method=RequestMethod.POST)
	public ModelAndView addCourse(@Valid @ModelAttribute("course")Course course, BindingResult validate) throws CourseErrorException
	{		
		try {
			// Validate the form, if failed, return previous view
			if(validate.hasErrors())
			{
				ModelAndView mv = new ModelAndView("course/courseAdd", "course", course);
				mv.addObject("courses", courseService.getAllCourses());
				return mv;
			}
			
			// Calls CourseBusinessService.createCourse() to add new Course
			courseService.addCourse(course);
			// If it was lower case after being verified and added, set to upper for the view.
			course.setCourseID(course.getCourseID().toUpperCase());
			// Forward user to the course View of new class.
			ModelAndView mv = new ModelAndView("course/courseView", "course", course);
			mv.addObject("courses", courseService.getAllCourses());
			return mv;
		}
		// Return Previous MAV w/ Error
		catch(CourseFoundException e)
		{
			ModelAndView mv = new ModelAndView("course/courseAdd");
			mv.addObject("course", course);
			mv.addObject("courses", courseService.getAllCourses());
			mv.addObject("error", "Course ID already exists.");
			return mv;
		}
		// If there was a system side issue
		catch(CourseErrorException e)
		{
			// Nav the user to the login screen if there is a system error
			ModelAndView mv = new ModelAndView("user/userLogin");
			mv.addObject("error", "Internal System Error. Returning user to login.");
			return mv;
		}
	}
	
	/**
	 * Navigates the user to a new add course page
	 * 
	 * @return ModelAndView addCourse.jsp
	 */
	@RequestMapping(path="/addCourse", method=RequestMethod.GET)
	public ModelAndView addNewCourse()
	{
		try 
		{
			// Nav User to the course view
			ModelAndView mv = new ModelAndView("course/courseAdd", "course", new Course());
			mv.addObject("courses", courseService.getAllCourses());
			return mv;
		}
		// If there was a system side issue
		catch(CourseErrorException e)
		{
			// Nav the user to the login screen if there is a system error
			ModelAndView mv = new ModelAndView("user/userLogin");
			mv.addObject("error", "Internal System Error. Returning user to login.");
			return mv;
		}
	}
	
	/**
	 * Navs the user to the modify page of course. Checks if course exists.
	 * 
	 * @param Course course
	 * @return ModelAndView
	 * @throws CourseErrorException
	 */
	@RequestMapping(path="/gotoModifyCourse", method=RequestMethod.POST)
	public ModelAndView gotoModifyCourse(@ModelAttribute("course") Course course) throws CourseErrorException
	{
		try
		{
			// Calls CourseService.getOneCourse() to get return the request course
			ModelAndView mv = new ModelAndView("course/courseModify", "course", courseService.getOneCourse(course));
			mv.addObject("courses", courseService.getAllCourses());
			return mv;
		}
		// The selected Course, after being verified, does not match an existing course
		catch(CourseNotFoundException e)
		{
			ModelAndView mv = new ModelAndView("course/courseView");
			mv.addObject("course", course);
			mv.addObject("courses", courseService.getAllCourses());
			mv.addObject("error", "Course ID does not match with an existing course. Please try again or add a new course.");
			return mv;
		}
		// If there was a system side issue
		catch(CourseErrorException e)
		{
			// Nav the user to the login screen if there is a system error
			ModelAndView mv = new ModelAndView("user/userLogin");
			mv.addObject("error", "Internal System Error. Returning user to login.");
			return mv;
		}
	}
	
	/**
	 * Collects the Form Post of modified course fields. Validates the course and saves the changes.
	 * 
	 * @param Course course
	 * @param BindingResult validate
	 * @return ModelAndView
	 * @throws CourseErrorException
	 */
	@RequestMapping(path="/submitModifyCourse", method=RequestMethod.POST)
	public ModelAndView submitModifyCourse(@Valid @ModelAttribute("course") Course course, BindingResult validate) throws CourseErrorException
	{
		try
		{
			// Validate the form
			if(validate.hasErrors())
			{
				ModelAndView mv = new ModelAndView("course/courseModify", "course", course);
				mv.addObject("courses", courseService.getAllCourses());
				return mv;
			}
			
			// Calls the CourseBusinessService.modifyCourse to handle the update
			courseService.modifyCourse(course);
			
			// Return MAV with success message
			ModelAndView mv = new ModelAndView("course/courseView");
			mv.addObject("course", course);
			mv.addObject("courses", courseService.getAllCourses());
			mv.addObject("success", "Course was successfully updated.");
			return mv;
		}
		catch(CourseNotFoundException e)
		{
			ModelAndView mv = new ModelAndView("course/courseModify");
			mv.addObject("course", course);
			mv.addObject("courses", courseService.getAllCourses());
			mv.addObject("error", "Course ID does not match with an existing course. Please try again or add a new course.");
			return mv;
		}
		// If there was a system side issue
		catch(CourseErrorException e)
		{
			// Nav the user to the login screen if there is a system error
			ModelAndView mv = new ModelAndView("user/userLogin");
			mv.addObject("error", "Internal System Error. Returning user to login.");
			return mv;
		}
	}
	
	/**
	 * Navs to the confirmation delete course view. Data is not modifiable
	 * 
	 * @param Course course
	 * @return ModelAndView
	 */
	@RequestMapping(path="/gotoDeleteCourse", method=RequestMethod.POST)
	public ModelAndView gotoDeleteCourse(@ModelAttribute("course") Course course) throws CourseErrorException
	{
		try
		{
			// Check if the course exists in the Database.
			course = courseService.getOneCourse(course);
			
			// Build view w/ course and dependencies
			ModelAndView mv = new ModelAndView("course/courseDelete", "course", course);
			mv.addObject("courses", courseService.getAllCourses());
			return mv;
		}
		catch(CourseNotFoundException e)
		{
			ModelAndView mv = new ModelAndView("course/courseView");
			mv.addObject("course", course);
			mv.addObject("courses", courseService.getAllCourses());
			mv.addObject("error", "Course ID does not match with an existing course. Please try again or add a new course.");
			return mv;
		}
		// If there was a system side issue
		catch(CourseErrorException e)
		{
			// Nav the user to the login screen if there is a system error
			ModelAndView mv = new ModelAndView("user/userLogin");
			mv.addObject("error", "Internal System Error. Returning user to login.");
			return mv;
		}
	}
	
	/**
	 * Confirmation on delete view submission request. Validates and checks for course before deleting.
	 * 
	 * @param Course course
	 * @return ModelAndView
	 * @throws CourseErrorException
	 */
	@RequestMapping(path="/submitDeleteCourse", method=RequestMethod.POST)
	public ModelAndView submitDeleteCourse(@ModelAttribute("course") Course course) throws CourseErrorException
	{
		try
		{
			// Calls CourseBusinessService to handle the delete request
			courseService.removeCourse(course);
			
			ModelAndView mv = new ModelAndView("dashboard");
			mv.addObject("course", course);
			mv.addObject("courses", courseService.getAllCourses());
			mv.addObject("success", "Course successfully removed.");
			return mv;
		}
		catch(CourseNotFoundException e)
		{
			ModelAndView mv = new ModelAndView("dashboard");
			mv.addObject("course", course);
			mv.addObject("courses", courseService.getAllCourses());
			mv.addObject("error", "Course ID did not reflect any existing courses.");
			return mv;
		}
		// If there was a system side issue
		catch(CourseErrorException e)
		{
			// Nav the user to the login screen if there is a system error
			ModelAndView mv = new ModelAndView("user/userLogin");
			mv.addObject("error", "Internal System Error. Returning user to login.");
			return mv;
		}
	}
	
	/**
	 * Takes a param that behaves a search.
	 * 
	 * @param String param
	 * @return ModelAndView
	 * @throws CourseErrorException
	 */
	@RequestMapping(value = "/{param}", method = RequestMethod.GET)
	public ModelAndView urlCourseSearch(@PathVariable("param") String param) throws CourseErrorException 
	{
		try
		{
			// Set the Course param ID to a model
			Course course = new Course(); 
			course.setCourseID(param);
			
			// Connects to the CourseBusinessService to get Course by ID
			course = courseService.getOneCourse(course);
			
			// Return MAV of 
			ModelAndView mv = new ModelAndView("course/courseView");
			mv.addObject("courses", courseService.getAllCourses());
			mv.addObject("course", course);
			return mv;
		}
		// When the selected course is not actually in the system
		catch(CourseNotFoundException e)
		{
			ModelAndView mv = new ModelAndView("dashboard");
			mv.addObject("courses", courseService.getAllCourses());
			mv.addObject("error", "Course Does Not Exist. Sorry!");
			return mv;
		}
		// If there was a system side issue
		catch(CourseErrorException e)
		{
			// Nav the user to the login screen if there is a system error
			ModelAndView mv = new ModelAndView("user/userLogin");
			mv.addObject("error", "Internal System Error. Returning user to login.");
			return mv;
		}
	}
}
