package com.airbus.product.service.exception;

public class GenericException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final String origin;
	private final String rootCause;
	private final String errorCode;

	public GenericException(final String origin, final String rootCause, final String message, final String errorCode, Throwable e) {
		super(message, e);
		this.origin = origin;
		this.rootCause = rootCause;
		this.errorCode = errorCode;
	}
	
	public GenericException(final String origin, final String rootCause, final String message, final String errorCode) {
		super(message);
		this.origin = origin;
		this.rootCause = rootCause;
		this.errorCode = errorCode;
	}
	
	public String getOrigin() {
		return origin;
	}
	public String getRootCause() {
		return rootCause;
	}
	public String getErrorCode() {
		return errorCode;
	}

}
