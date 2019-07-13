package com.revature.ctb.exceptions;

public class NotFoundRecordException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8959886439075280746L;
	
	public NotFoundRecordException() {
		// TODO Auto-generated constructor stub
	}

	public NotFoundRecordException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NotFoundRecordException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public NotFoundRecordException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NotFoundRecordException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
