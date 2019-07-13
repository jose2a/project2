package com.revature.ctb.exceptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom exception to send fields validations to the user.
 * User input validations.
 *
 */
public class BadRequestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2294655477120584783L;

	// Stores the validation errors in a list that way we can loop them in Angular
	private List<String> errors = new ArrayList<>();

	public BadRequestException() {
		// TODO Auto-generated constructor stub
	}

	public BadRequestException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public BadRequestException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public BadRequestException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BadRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Getting errors
	 * @return the errors
	 */
	public List<String> getErrors() {
		return errors;
	}

	/**
	 * Setting errors
	 * @param errors
	 */
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
	/**
	 * Adding errors to the list
	 * @param error Error
	 */
	public void addError(String error) {
		errors.add(error);
	}

}
