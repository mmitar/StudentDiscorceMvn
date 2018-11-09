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

@Controller
@RequestMapping("/course")
public class CourseController
{
	/**
	 * Dependency Injected
	 */
	@Autowired
	private CourseBusinessInterface courseService;
//	private ModelAndView mav;
//	
//	public CourseController()
//	{
//		this.mav = new ModelAndView();
//	}
	
	/**
	 * Navs the user to the courseList page via URI. Currently no model is currently implemented
	 * but one eventually be made further in the project.
	 *
	 * @return ModelAndView courseList
	 */
	@RequestMapping(path="/new", method=RequestMethod.GET)
	public ModelAndView displayForm()
	{
		//Return new MAV
		return new ModelAndView("course/courseList");
	}
	
	/**
	 * Collects from a form post the course Id and get the course information and passes
	 * it to the course view where all the data is populated.
	 * 
	 * @param course
	 * @return ModelAndView course, courseView
	 */
	@RequestMapping(path="/courseView", method=RequestMethod.POST)
	public ModelAndView displayCourse(@Valid @ModelAttribute("course")Course course, BindingResult validate) {

		if(validate.hasErrors())
		{
			return new ModelAndView("course/courseView", "course", course);
		}
		
		try
		{
			// Connects to the CourseBusinessService to get Course by ID
			course = courseService.getOneCourse(course);
			
			ModelAndView mv = new ModelAndView("course/courseView");
			mv.addObject("course", course);
			return mv;
		}
		catch(CourseNotFoundException e)
		{
			ModelAndView mv = new ModelAndView("course/courseView");
			mv.addObject("course", course);
			return mv;
		}
	}

	/**
	 * Checks the validation of course in the addCourse form. 
	 * Navs to the course view page if the model is valid and original
	 * 
	 * @param course
	 * @param result
	 * @return ModelAndView
	 */
	@RequestMapping(path="/addedCourse", method=RequestMethod.POST)
	public ModelAndView addCourse(@Valid @ModelAttribute("course")Course course, BindingResult validate)
	{		
		// Validate the form, if failed, return previous view
		if(validate.hasErrors())
		{
			return new ModelAndView("course/courseAdd", "course", course);
		}
		
		try {
			// Calls CourseBusinessService.createCourse() to add new Course
			courseService.addCourse(course);
			
			// Forward user to the course View of new class.
			return new ModelAndView("course/courseView", "course", course);
		}
		// Return Previous MAV w/ Error
		catch(CourseFoundException e)
		{
			ModelAndView mv = new ModelAndView("course/courseAdd");
			mv.addObject("course", course);
			mv.addObject("error", "Course ID already exists.");
			return mv;
		}
		// Return Previous MAV w/ Error
		catch(CourseErrorException e)
		{
			ModelAndView mv = new ModelAndView("course/courseAdd");
			mv.addObject("course", course);
			mv.addObject("error", "Error creating new course. Please try again.");
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
		//return MAV to addCourse
		return new ModelAndView("course/courseAdd", "course", new Course());
	}
	
	@RequestMapping(value = "/{param}", method = RequestMethod.GET)
	public ModelAndView urlCourseSearch(@PathVariable("param") String param) {
		
		// TODO: Course Search Page. Check for User Login in Cache. Return Search Page.
		return null;
	}
	
}
