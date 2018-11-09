package com.app.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ModelAndView handleIOException(Exception e) {
		
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("error", e.toString());
		return mv;
	}
}
