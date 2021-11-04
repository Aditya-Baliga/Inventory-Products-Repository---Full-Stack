package com.airbus.product.service.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.airbus.product.model.Error;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	public final ResponseEntity<CustomExceptionResponse> handleException(final IllegalArgumentException ex) {
		CustomExceptionResponse exceptionResponse = new CustomExceptionResponse(new Date(),
				"Product for the given ID not present in database", "NOT_FOUND", "404");
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(CreateResourceException.class)
	public final ResponseEntity<CustomExceptionResponse> handleException(final CreateResourceException ex) {
		CustomExceptionResponse exceptionResponse = new CustomExceptionResponse(new Date(), ex.getMessage(),
				ex.getStatus(), ex.getErrorCode());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(GetResourceException.class)
	public final ResponseEntity<CustomExceptionResponse> handleException(final GetResourceException ex) {
		CustomExceptionResponse exceptionResponse = new CustomExceptionResponse(new Date(), ex.getMessage(),
				ex.getStatus(), ex.getErrorCode());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DeleteResourceException.class)
	public final ResponseEntity<CustomExceptionResponse> handleException(final DeleteResourceException ex) {
		CustomExceptionResponse exceptionResponse = new CustomExceptionResponse(new Date(), ex.getMessage(),
				ex.getStatus(), ex.getErrorCode());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UpdateResourceException.class)
	public final ResponseEntity<CustomExceptionResponse> handleException(final UpdateResourceException ex) {
		CustomExceptionResponse exceptionResponse = new CustomExceptionResponse(new Date(), ex.getMessage(),
				ex.getStatus(), ex.getErrorCode());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(InputValidationException.class)
	public final ResponseEntity<CustomExceptionResponse> handleException(final InputValidationException ex) {
		CustomExceptionResponse exceptionResponse = new CustomExceptionResponse(new Date(), ex.getReason(),
				String.valueOf(ex.getStatus()), ex.getMessage());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.valueOf(ex.getStatus()));
	}

	@ExceptionHandler(GenericException.class)
	public final ResponseEntity<Error> handleException(final GenericException ex) {
		Error error = new Error();
		error.setMessage(ex.getMessage());
		error.setRootCause(ex.getRootCause());
		error.setErrorCode(ex.getErrorCode());
		error.setOrigin(ex.getOrigin());
		if ("409".equals(ex.getErrorCode())) {
			return new ResponseEntity<>(error, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Error> handleException(final Exception ex) {
		Error error = new Error();
		error.setMessage(ex.getMessage());

		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
