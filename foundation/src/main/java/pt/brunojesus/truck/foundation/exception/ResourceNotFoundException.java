package pt.brunojesus.truck.foundation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Class NotFoundException.
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Resource not found")
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8764362138061696025L;

	public ResourceNotFoundException() {
		super();
	}

	public ResourceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ResourceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}

	public ResourceNotFoundException(Throwable cause) {
		super(cause);
	}

}
