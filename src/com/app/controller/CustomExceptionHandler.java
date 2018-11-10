package com.app.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.app.exceptions.DatabaseException;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(DatabaseException.class)
	public ModelAndView handleDatabaseException(Exception e) 
	{
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("error", e.toString());
		return mv;
	}
}
