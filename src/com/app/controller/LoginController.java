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
import com.app.business.UserBusinessInterface;
import com.app.exceptions.CourseErrorException;
import com.app.exceptions.UserNotFoundException;
import com.app.model.User;

/**
 * Controller for handling existing account input and view building
 */
@Controller
@RequestMapping("/login")
public class LoginController 
{
	/**
	 * Dependency Injected
	 */
	@Autowired
	private UserBusinessInterface userService;
	
	@Autowired
	private CourseBusinessInterface courseService;
	
	/**
	 * Navigates the user via URI to the login view with an empty User model
	 * 
	 * @return ModelAndView loginUser
	 */
	@RequestMapping(path="/user", method=RequestMethod.GET)
	public ModelAndView displayForm()
	{
		// Navigate the user to the login view with a blank form
		return new ModelAndView("user/userLogin", "user", new User());
	}
	
	/**
	 * Validates the form post user model for validation errors. if error: nav back to loginUser
	 * If successful, nav forward to dashboard.
	 * 
	 * @param User user
	 * @param BindingResult result
	 * @return ModelAndView loginUser, user
	 * @return ModelAndView dashboard, user
	 */
	@RequestMapping(path="/validateUser", method=RequestMethod.POST)
	public ModelAndView loginUser(@Valid @ModelAttribute("user")User user, BindingResult validate)
	{
		// Validate the form
		if(validate.hasErrors())
		{
			// If form is invalid, return to login view with existing input
			return new ModelAndView("user/userLogin", "user", user);
		}
		
		try {
			// Call UserBusinessService.findBy() to see if user exists
			User verifiedUser = userService.authenticateUser(user);
			
			// Forwards the user to the dashboard if User found
			ModelAndView mv = new ModelAndView("dashboard");
			mv.addObject("user", verifiedUser);
			mv.addObject("courses", courseService.getAllCourses());
			return mv;
		}
		// If the credentials do not match an existing user
		catch(UserNotFoundException e)
		{
			// Return User back to the login view w/ Error Message
			ModelAndView mv = new ModelAndView("user/userLogin");
			mv.addObject("user", user);
			mv.addObject("error", "Username or Password is incorrect. \nFields are Case-Sensitive.");
			return mv;
		}
		// If there was a system side issue
		catch(CourseErrorException e)
		{
			// Continue to the dashboard without the courses after user was validated
			ModelAndView mv = new ModelAndView("dashboard");
			mv.addObject("user", user);
			mv.addObject("courses", null);
			mv.addObject("error", "Error collecting Courses.");
			return mv;
		}
	}
	
	/**
	 * Navs user the dashboard via URI.
	 * 
	 * @return View dashboard
	 */
	@RequestMapping(path="/dashboard", method=RequestMethod.GET)
	public ModelAndView viewDashboard() 
	{
		try
		{
			// Navigate the user to the dashboard
			ModelAndView mv = new ModelAndView("dashboard");
			mv.addObject("courses", courseService.getAllCourses());
			return mv;
		}
		// If there was a system side issue
		catch(CourseErrorException e)
		{
			// Continue to the dashboard without the courses
			ModelAndView mv = new ModelAndView("dashboard");
			mv.addObject("courses", null);
			mv.addObject("error", "Error collecting Courses.");
			return mv;
		}
	}
	
	/**
	 * Setup Layouts for the error page
	 */
	@RequestMapping(path="/error", method=RequestMethod.GET)
	public ModelAndView viewErrorPage()
	{
		// Navigate the user to the dashboard
		ModelAndView mv = new ModelAndView("error");
		return mv;
	}
}