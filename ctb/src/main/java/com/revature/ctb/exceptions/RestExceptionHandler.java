package com.revature.ctb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.revature.ctb.utils.LogUtil;

@ControllerAdvice
public class RestExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handlerException(NotAuthenticatedException exc) {
		LogUtil.trace("RestExceptionHandler - handlerException(NotAuthenticatedException exc)");

		ErrorResponse error = new ErrorResponse();
		error.setStatus(HttpStatus.UNAUTHORIZED.value());
		error.setTitle(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
	}

	/**
	 * Catching NoFoundRecordException (custom exception) to indicate that the
	 * record was not found
	 * 
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
	 * Catching DuplicateRecordException (custom exception) to indicate that the
	 * record is duplicated based on what business rule we follow
	 * 
	 * @param exc The exception
	 * @return Error with the messages
	 */
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handlerException(DuplicateRecordException exc) {
		LogUtil.trace("RestExceptionHandler - handlerException(DuplicateRecordException exc)");

		ErrorResponse error = new ErrorResponse();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setTitle("Duplicate");
		error.setErrorMessages(exc.getErrors());
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Catching InputValidationException (custom exception) to indicate that the user
	 * enter invalid inputs
	 * 
	 * @param exc The exception
	 * @return Error with the messages
	 */
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handlerException(InputValidationException exc) {
		LogUtil.trace("RestExceptionHandler - handlerException(InputValidationException exc)");

		ErrorResponse error = new ErrorResponse();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setTitle(exc.getMessage());
		error.setErrorMessages(exc.getErrors());
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Catching MethodArgumentNotValidException to indicate that the user enter
	 * invalid inputs
	 * 
	 * @param exc The exception
	 * @return Error with the messages
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException ex) {
		LogUtil.debug("handleException(MethodArgumentNotValidException ex)");

		ErrorResponse errorResponse = new ErrorResponse();

		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setTitle("Input validation errors");
		errorResponse.setTimeStamp(System.currentTimeMillis());

		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errorResponse.getErrorMessages().add(error.getDefaultMessage()); // putting the error in the list
		}

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Catching any Exception (catch all) like a global error handler
	 * 
	 * @param ex The exception
	 * @return Error with the messages
	 */
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(Exception ex) {
		LogUtil.debug("RestExceptionHandler - handlerException(Exception exc)");

		LogUtil.error(ex.getMessage()); // Saving error to the logs

		// create a ErrorResponse
		ErrorResponse error = new ErrorResponse();

		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setTitle(ex.getMessage());
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
