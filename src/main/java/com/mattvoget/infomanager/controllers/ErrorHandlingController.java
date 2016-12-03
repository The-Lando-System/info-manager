package com.mattvoget.infomanager.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mattvoget.infomanager.dtos.ExceptionResponse;

public class ErrorHandlingController {

	
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler
	public @ResponseBody ExceptionResponse unknownException(Exception e) {
		return new ExceptionResponse(e.getMessage(), e.getClass().getName());
	}
	
}
