package com.revature.ctb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.revature.ctb.utils.LogUtil;

@ControllerAdvice
public class RestExceptionHandler {

	/**
	 * Catching NoFoundRecordException (custom exception) to indicate that the record was not found
	 * @param exc The exception
	 * @return Error with the messages
	 */
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handlerException(NotFoundRecordException exc) {
		LogUtil.trace("RestExceptionHandler - handlerException(NotFoundRecordException exc)");

		ErrorResponse error = new ErrorResponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setTitle(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	/**
	 * Catching DuplicateRecordException (custom exception) to indicate that the record is duplicated
	 * based on what business rule we follow
	 * @param exc The exception
	 * @return Error with the messages
	 */
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handlerException(DuplicateRecordException exc) {
		LogUtil.trace("RestExceptionHandler - handlerException(DuplicateRecordException exc)");

		ErrorResponse error = new ErrorResponse();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setTitle(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}


	/**
	 * Catching BadRequestException (custom exception) to indicate that the user enter
	 * invalid inputs
	 * @param exc The exception
	 * @return Error with the messages
	 */
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handlerException(BadRequestException exc) {
		LogUtil.trace("RestExceptionHandler - handlerException(BadRequestException exc)");

		ErrorResponse error = new ErrorResponse();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setTitle(exc.getMessage());
		error.setErrorMessages(exc.getErrors());
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	// add another exception handler ... to catch any exception (catch all)
	/**
	 * Catching any Exception (catch all) like a global error handler
	 * @param exc The exception
	 * @return Error with the messages
	 */
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(Exception exc) {
		LogUtil.trace("RestExceptionHandler - handlerException(Exception exc)");
		
		LogUtil.error(exc.getMessage()); // Saving error to the logs

		// create a ErrorResponse
		ErrorResponse error = new ErrorResponse();
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setTitle(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
