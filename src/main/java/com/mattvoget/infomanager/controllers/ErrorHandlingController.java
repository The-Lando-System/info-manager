package com.mattvoget.infomanager.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mattvoget.infomanager.dtos.InfoManagerException;

public class ErrorHandlingController {

	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	@ExceptionHandler
	public @ResponseBody InfoManagerException illegalAccessException(IllegalAccessError iae) {
		return new InfoManagerException(iae.getMessage(), iae.getClass().getName(), HttpStatus.BAD_REQUEST);
	}
	
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler
	public @ResponseBody InfoManagerException unknownException(RuntimeException e) {
		return new InfoManagerException(e.getMessage(), e.getClass().getName(), HttpStatus.INTERNAL_SERVER_ERROR);
	}


}
