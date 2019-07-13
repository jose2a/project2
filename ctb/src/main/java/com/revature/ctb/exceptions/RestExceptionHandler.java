package com.revature.ctb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

	// catching not found record exceptions
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handlerException(NotFoundRecordException exc) {

		ErrorResponse error = new ErrorResponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setTitle(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	// catching duplicate record exceptions based on the validation criteria
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handlerException(DuplicateRecordException exc) {

		ErrorResponse error = new ErrorResponse();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setTitle(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	// catching validation exceptions
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handlerException(BadRequestException exc) {
		System.out.println("Other exceptions");

		ErrorResponse error = new ErrorResponse();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setTitle(exc.getMessage());
		error.setErrorMessages(exc.getErrors());
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	// add another exception handler ... to catch any exception (catch all)
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(Exception exc) {

		// create a StudentErrorResponse
		ErrorResponse error = new ErrorResponse();
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setTitle(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
