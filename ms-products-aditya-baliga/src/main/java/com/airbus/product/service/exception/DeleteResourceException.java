package com.airbus.product.service.exception;

public class DeleteResourceException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private final String errorCode;
	private final String status;
	
	public DeleteResourceException(final String message, final  String status, final String errorCode, final Throwable e) {
		super(message, e);
		this.errorCode = errorCode;
		this.status = status;
	
	}
	
	public DeleteResourceException(final String message, final  String status, final String errorCode) {
		super(message);
		this.errorCode = errorCode;
		this.status = status;
	
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getStatus() {
		return status;
	}
	
	
}
