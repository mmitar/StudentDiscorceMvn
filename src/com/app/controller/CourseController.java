package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.app.business.CourseBusinessInterface;
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
	
	/**
	 * displayForm
	 * Navs the user to the courseList page via URI. Currently no model is currently implemented
	 * but one eventually be made further in the project.
	 *
	 * @return ModelAndView courseList
	 */
	@RequestMapping(path="/new", method=RequestMethod.GET)
	public ModelAndView displayForm()
	{
		//Return new MAV
		return new ModelAndView("courseList");
	}
	
	/**
	 * displayCourse
	 * Collects from a form post the course Id and get the course information and passes
	 * it to the course view where all the data is populated.
	 * 
	 * @param course
	 * @return ModelAndView course, courseView
	 */
	@RequestMapping(path="/courseView", method=RequestMethod.POST)
	public ModelAndView displayCourse(@ModelAttribute("course")Course course) {

		// Connects to the CourseBusinessService to get Course by ID
		course = courseService.findBy(course);
		
		// Construct MAV
		ModelAndView mv = new ModelAndView("courseView");
		mv.addObject("course", course);
		
		return mv;
	}

	/**
	 * addCourse
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
		// Validate the form
		if(validate.hasErrors())
		{
			// Return Previous MAV
			return new ModelAndView("addCourse", "course", course);
		}
		
		// Calls CourseBusinessService.createCourse() to add new Course
		boolean result = courseService.createCourse(course);
		
		// Verifies if creating a course worked. If false, course already exists.
		if(result == false) 
		{
			// Return Previous MAV w/ Error
			ModelAndView mv = new ModelAndView("addCourse");
			mv.addObject("course", course);
			mv.addObject("error", "Course ID already exists.");
			return mv;
		}
		
		// Forward user to the course View of new class.
		return new ModelAndView("courseView", "course", course);
	}
	
	/**
	 * addNewCourse
	 * Navigates the user to a new add course page
	 * 
	 * @return ModelAndView addCourse.jsp
	 */
	@RequestMapping(path="/addCourse", method=RequestMethod.GET)
	public ModelAndView addNewCourse()
	{
		//return MAV to addCourse
		return new ModelAndView("addCourse", "course", new Course());
	}
	
}
