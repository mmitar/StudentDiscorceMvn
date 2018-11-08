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
	 * loginUser
	 * Validates the form post user model for validation errors. if error: nav back to loginUser
	 * if successful, nav forward to dashboard.
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
			return new ModelAndView("loginUser", "user", user);
		}
		
		// Call UserBusinessService.findBy() to see if user exists
		User verifiedUser = this.userService.findBy(user);
		
		// check if the User was found. If not return back to previous view with error
		if(verifiedUser == null)
		{
			ModelAndView mv = new ModelAndView("loginUser");
			mv.addObject("user", user);
			mv.addObject("error", "Username or Password is incorrect.");
			return mv;
		}
		
		// Forwards the user to the dashboard if User found
		ModelAndView mv = new ModelAndView("dashboard");
		mv.addObject("user", verifiedUser);
		mv.addObject("courses", this.courseService.findAll());
		
		return mv;
	}
	
	/**
	 * viewDashboard
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
