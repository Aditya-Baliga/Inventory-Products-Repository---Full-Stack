package com.airbus.product.service.exception;

public class GetResourceException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final String errorCode;
	private final String status;
	
	public GetResourceException(final String message, final  String status, final String errorCode, final Throwable e) {
		super(message, e);
		this.errorCode = errorCode;
		this.status = status;
	
	}
	
	public GetResourceException(final String message, final  String status, final String errorCode) {
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
