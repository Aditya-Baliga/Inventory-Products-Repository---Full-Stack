package com.airbus.product.service.exception;

import java.util.Date;

public class CustomExceptionResponse {
	private final Date timestamp;
	private final String message;
	private final String status;
	private final String errorCode;
	
	public CustomExceptionResponse(Date timestamp, String message, String status, String errorCode) {
		super();
		this.timestamp = (Date)timestamp.clone();
		this.message = message;
		this.status = status;
		this.errorCode = errorCode;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public String getMessage() {
		return message;
	}
	public String getStatus() {
		return status;
	}
	public String getErrorCode() {
		return errorCode;
	}

}
