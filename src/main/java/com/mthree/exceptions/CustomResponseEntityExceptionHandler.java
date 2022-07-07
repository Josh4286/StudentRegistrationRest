package com.mthree.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//@RestController
//@ControllerAdvise
@RestControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class) //used for all exceptions
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public ExceptionResponse handleAllExceptions(Exception ex, WebRequest req) {
		System.out.println("inside handleAllExceptions");
		ExceptionResponse exRes = new ExceptionResponse(new Date(), ex.getMessage(), "Detailed description of an exception");
		//return new ResponseEntity<Object>(exRes,HttpStatus.INTERNAL_SERVER_ERROR);
		return exRes;
	}
	
	@ExceptionHandler({UserNotFoundException.class}) //used for usernotfound exceptions
	@ResponseStatus(code=HttpStatus.NOT_FOUND)
	public ExceptionResponse handleUserNotFoundException(UserNotFoundException ex, WebRequest req) {
		System.out.println("inside handleUserNotFoundException");
		ExceptionResponse exRes = new ExceptionResponse(new Date(), ex.getMessage(), "The user with the requested ID is not available");
		//return new ResponseEntity<Object>(exRes,HttpStatus.NOT_FOUND);
		return exRes;
	}
	
	@ExceptionHandler({UserAlreadyExistsException.class}) //used for usernotfound exceptions
	@ResponseStatus(code=HttpStatus.CONFLICT)
	public ExceptionResponse handleUserAlreadyExistsException(UserAlreadyExistsException ex, WebRequest req) {
		System.out.println("inside handleUserAlreadyExistsException");
		ExceptionResponse exRes = new ExceptionResponse(new Date(), ex.getMessage(), "The user cannot be added to database as it already exists");
		//return new ResponseEntity<Object>(exRes,HttpStatus.NOT_FOUND);
		return exRes;
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse exRes = new ExceptionResponse(new Date(), "validation error", ex.getBindingResult().toString());
		return new ResponseEntity<Object>(exRes, HttpStatus.BAD_REQUEST);
	}
	
	
}
