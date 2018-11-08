package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.app.business.UserBusinessInterface;
import com.app.model.User;

@Controller
@RequestMapping("/register")
public class RegisterController 
{
	/**
	 * Dependency Injected
	 */
	@Autowired
	private UserBusinessInterface userService;
	
	/**
	 * displayForm
	 * Navigates the user via URI to the register view with an empty User model
	 * 
	 * @return ModelAndView registerUser 
	 */
	@RequestMapping(path="/user", method=RequestMethod.GET)
	public ModelAndView displayForm()
	{
		return new ModelAndView("registerUser", "user", new User());
	}
	
	/**
	 * registerUser
	 * Registers the Form Post User Model for an validation errors. Navs back if error,
	 * navs to loginUser view if successful.
	 * 
	 * @param User user
	 * @param BindingResult result
	 * @return ModelAndView registerUser, user
	 * @return ModelAndView Login, user
	 */
	@RequestMapping(path="/registerUser", method=RequestMethod.POST)
	public ModelAndView registerUser(@Valid @ModelAttribute("user")User user, BindingResult validate)
	{
		// Validate the form, if errors return view
		if(validate.hasErrors())
		{
			return new ModelAndView("registerUser", "user", user);
		}
		
		// Calls UserBusinessService.create()
		boolean result = this.userService.create(user);
		
		// If create failed return with error
		if(result == false)
		{
			ModelAndView mv = new ModelAndView("registerUser");
			mv.addObject("user", user);
			mv.addObject("error", "Username already exists. Please try another.");
			return mv;
		}
		
		// Return MAV and user model to login form
		return new ModelAndView("loginUser", "user", user);
	}
	
}
