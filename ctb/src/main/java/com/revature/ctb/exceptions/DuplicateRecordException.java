package com.revature.ctb.exceptions;

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

}
