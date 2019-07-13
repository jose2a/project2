package com.revature.ctb.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {
	private int status;
	private String title;
	private List<String> errorMessages;
	private long timeStamp;

	public ErrorResponse() {
		errorMessages = new ArrayList<>();
	}

	public ErrorResponse(int status, String title, long timeStamp) {
		this();
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
