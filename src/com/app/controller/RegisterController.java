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
import com.app.exceptions.UserErrorException;
import com.app.exceptions.UserFoundException;
import com.app.model.User;

/**
 * Controller for registering new users and verifying the new account and view building
 */
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
	 * Navigates the user via URI to the register view with an empty User model
	 * 
	 * @return ModelAndView registerUser 
	 */
	@RequestMapping(path="/user", method=RequestMethod.GET)
	public ModelAndView displayForm()
	{
		// Nav to register view with a blank form
		return new ModelAndView("user/userRegister", "user", new User());
	}
	
	/**
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
		// Validate the form
		if(validate.hasErrors())
		{
			// If error, return to register view with existing input
			return new ModelAndView("user/userRegister", "user", user);
		}
		
		try
		{
			// Calls UserBusinessService.create()
			userService.addUser(user);

			// Return MAV and user model to login form
			ModelAndView mv = new ModelAndView("user/userLogin");
			mv.addObject("user", user);
			mv.addObject("success", "You are successfully registered!");
			return mv;
			
		}
		// If create failed return with error
		catch(UserFoundException e)
		{
			ModelAndView mv = new ModelAndView("user/userRegister");
			mv.addObject("user", user);
			mv.addObject("error", "Username already exists. Please try another.");
			return mv;
		}
		// If there was a system side issue.
		catch(UserErrorException e)
		{
			ModelAndView mv = new ModelAndView("user/userRegister");
			mv.addObject("user", user);
			mv.addObject("error", "There was an issue creating your account. Please try again.");
			return mv;
		}
	}
}
