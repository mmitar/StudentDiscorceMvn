package com.app.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.app.exceptions.DatabaseException;

/**
 * Global Exception Handler Controller that catches all uncaught exceptions
 */
@ControllerAdvice
public class CustomExceptionHandler {

	/**
	 * Catches DatabaseExceptions and assembles an error page.
	 * 
	 * @param Exception e
	 * @return ModelAndView
	 */
	@ExceptionHandler(DatabaseException.class)
	public ModelAndView handleDatabaseException(Exception e) 
	{
		// Return MAV with the error
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("error", e.toString());
		return mv;
	}
}
