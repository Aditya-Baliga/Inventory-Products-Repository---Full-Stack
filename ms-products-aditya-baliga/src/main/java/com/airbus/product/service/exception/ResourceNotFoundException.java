package com.airbus.product.service.exception;

public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(final String message) {
		super(message);
	}
	
	public ResourceNotFoundException(final String message, final Throwable e) {
		super(message, e);
	}
	
}
