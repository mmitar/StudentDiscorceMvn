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
	 * displayForm
	 * Navigates the user via URI to the login view with an empty User model
	 * 
	 * @return ModelAndView loginUser
	 */
	@RequestMapping(path="/user", method=RequestMethod.GET)
	public ModelAndView displayForm()
	{
		return new ModelAndView("loginUser", "user", new User());
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
			System.out.println("user has errors: " + user);
			return new ModelAndView("loginUser", "user", user);
		}
		
		try {
			// Call UserBusinessService.findBy() to see if user exists
			User verifiedUser = userService.findBy(user);
			
			// Forwards the user to the dashboard if User found
			ModelAndView mv = new ModelAndView("dashboard");
			mv.addObject("user", verifiedUser);
			mv.addObject("courses", courseService.findAll());
			return mv;
		}
		// 
		catch(UserNotFoundException e)
		{
			System.out.println("UserNotFoundException.");
			ModelAndView mv = new ModelAndView("loginUser");
			mv.addObject("user", user);
			mv.addObject("error", "Username or Password is incorrect.");
			return mv;
		}
		catch(CourseErrorException e)
		{
			System.out.println("CourseErrorException.");
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
	public String viewDashboard() 
	{
		return "dashboard";
	}
	
}