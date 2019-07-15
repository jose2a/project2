package com.revature.ctb.exceptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom exception to send message to the user that something was already
 * inserted in the database following a certain business rule
 *
 */
public class DuplicateRecordException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7476675595776336292L;

	// Stores the validation errors in a list that way we can loop them in Angular
	private List<String> errors = new ArrayList<>();

	public DuplicateRecordException() {
		// TODO Auto-generated constructor stub
	}

	public DuplicateRecordException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DuplicateRecordException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public DuplicateRecordException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DuplicateRecordException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Getting errors
	 * 
	 * @return the errors
	 */
	public List<String> getErrors() {
		return errors;
	}

	/**
	 * Setting errors
	 * 
	 * @param errors
	 */
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	/**
	 * Adding errors to the list
	 * 
	 * @param error Error
	 */
	public void addError(String error) {
		errors.add(error);
	}

}
