package com.mindtree.kalingahospital.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindtree.kalingahospital.exceptions.ApplicationException;
import com.mindtree.kalingahospital.exceptions.ErrorMessage;

@RestControllerAdvice
public class ControllerHandler {
	
	@ExceptionHandler(ApplicationException.class)
	public ResponseEntity<ErrorMessage> getErrorMessage(ApplicationException e)
	{
	
	ErrorMessage errorObj=new ErrorMessage(e.getMessage());
	return new ResponseEntity<ErrorMessage>(errorObj,new HttpHeaders(),HttpStatus.BAD_REQUEST);
	}

}
