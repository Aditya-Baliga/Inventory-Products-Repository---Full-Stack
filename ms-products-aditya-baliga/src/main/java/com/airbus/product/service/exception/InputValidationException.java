package com.airbus.product.service.exception;

public class InputValidationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private final String reason;
	private final int status;
	
	
	public InputValidationException(final String message, final int status, final String reason) {
		super(message);
		this.reason = reason;
		this.status = status;
	
	}
	
	public String getReason() {
		return reason;
	}
	public int getStatus() {
		return status;
	}
}
