package com.revature.ctb.exceptions;

import java.util.List;

/**
 * Error that will be send to the client, with all the required information
 *
 */
public class ErrorResponse {
	
	private int status; // status (400, 500, etc)
	private String title; // Title of the error
	private List<String> errorMessages; // Messages we want to show to the user 
	private long timeStamp; // Time of the error

	public ErrorResponse() {
	}

	public ErrorResponse(int status, String title, long timeStamp) {
		super();
		this.status = status;
		this.title = title;
		this.timeStamp = timeStamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

}
