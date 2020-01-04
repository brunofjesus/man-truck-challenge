package pt.brunojesus.truck.facade.api;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import pt.brunojesus.truck.codegen.dto.ErrorDTO;
import pt.brunojesus.truck.foundation.exception.ResourceNotFoundException;

/**
 * The Class RestExceptionHandler.
 * 
 * Handles errors and returns an response entity with a suitable ErrorDTO
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	// 400

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		final List<String> errors = new ArrayList<String>();
		for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}

		final ErrorDTO errorDTO = new ErrorDTO().id(HttpStatus.BAD_REQUEST.value())
				.message(ex.getLocalizedMessage() + " - " + errors);
		return new ResponseEntity<Object>(errorDTO, HttpStatus.valueOf(errorDTO.getId()));
	}

	@Override
	protected ResponseEntity<Object> handleBindException(final BindException ex, final HttpHeaders headers,
			final HttpStatus status, final WebRequest request) {
		final List<String> errors = new ArrayList<String>();
		for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}
		final ErrorDTO errorDTO = new ErrorDTO().id(HttpStatus.BAD_REQUEST.value())
				.message(ex.getLocalizedMessage() + " - " + errors);
		return new ResponseEntity<Object>(errorDTO, HttpStatus.valueOf(errorDTO.getId()));
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(final TypeMismatchException ex, final HttpHeaders headers,
			final HttpStatus status, final WebRequest request) {
		final String error = ex.getValue() + " value for " + ex.getPropertyName() + " should be of type "
				+ ex.getRequiredType();

		final ErrorDTO errorDTO = new ErrorDTO().id(HttpStatus.BAD_REQUEST.value())
				.message(ex.getLocalizedMessage() + " - " + error);
		return new ResponseEntity<Object>(errorDTO, HttpStatus.valueOf(errorDTO.getId()));
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestPart(final MissingServletRequestPartException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		final String error = ex.getRequestPartName() + " part is missing";
		final ErrorDTO errorDTO = new ErrorDTO().id(HttpStatus.BAD_REQUEST.value())
				.message(ex.getLocalizedMessage() + " - " + error);
		return new ResponseEntity<Object>(errorDTO, HttpStatus.valueOf(errorDTO.getId()));
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(
			final MissingServletRequestParameterException ex, final HttpHeaders headers, final HttpStatus status,
			final WebRequest request) {
		final String error = ex.getParameterName() + " parameter is missing";
		final ErrorDTO errorDTO = new ErrorDTO().id(HttpStatus.BAD_REQUEST.value())
				.message(ex.getLocalizedMessage() + " - " + error);
		return new ResponseEntity<Object>(errorDTO, HttpStatus.valueOf(errorDTO.getId()));
	}

	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(final MethodArgumentTypeMismatchException ex,
			final WebRequest request) {
		final String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();

		final ErrorDTO errorDTO = new ErrorDTO().id(HttpStatus.BAD_REQUEST.value())
				.message(ex.getLocalizedMessage() + " - " + error);
		return new ResponseEntity<Object>(errorDTO, HttpStatus.valueOf(errorDTO.getId()));
	}

	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleConstraintViolation(final ConstraintViolationException ex,
			final WebRequest request) {
		final List<String> errors = new ArrayList<String>();
		for (final ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": "
					+ violation.getMessage());
		}

		final ErrorDTO errorDTO = new ErrorDTO().id(HttpStatus.BAD_REQUEST.value())
				.message(ex.getLocalizedMessage() + " - " + errors);
		return new ResponseEntity<Object>(errorDTO, HttpStatus.valueOf(errorDTO.getId()));
	}

	// 404

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(final NoHandlerFoundException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		final String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();

		final ErrorDTO errorDTO = new ErrorDTO().id(HttpStatus.NOT_FOUND.value())
				.message(ex.getLocalizedMessage() + " - " + error);
		return new ResponseEntity<Object>(errorDTO, HttpStatus.valueOf(errorDTO.getId()));

	}

	@ExceptionHandler({ ResourceNotFoundException.class })
	public ResponseEntity<Object> handleResourceNotFound(final ResourceNotFoundException ex, final WebRequest request) {
		final ErrorDTO errorDTO = new ErrorDTO().id(HttpStatus.NOT_FOUND.value()).message(ex.getLocalizedMessage());
		return new ResponseEntity<Object>(errorDTO, HttpStatus.valueOf(errorDTO.getId()));
	}

	// 405

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			final HttpRequestMethodNotSupportedException ex, final HttpHeaders headers, final HttpStatus status,
			final WebRequest request) {
		final StringBuilder builder = new StringBuilder();
		builder.append(ex.getMethod());
		builder.append(" method is not supported for this request. Supported methods are ");
		ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));

		final ErrorDTO errorDTO = new ErrorDTO().id(HttpStatus.METHOD_NOT_ALLOWED.value())
				.message(ex.getLocalizedMessage() + " - " + builder.substring(0, builder.length() - 1));
		return new ResponseEntity<Object>(errorDTO, HttpStatus.valueOf(errorDTO.getId()));
	}

	// 415

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(final HttpMediaTypeNotSupportedException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		final StringBuilder builder = new StringBuilder();
		builder.append(ex.getContentType());
		builder.append(" media type is not supported. Supported media types are ");
		ex.getSupportedMediaTypes().forEach(t -> builder.append(t + " "));

		final ErrorDTO errorDTO = new ErrorDTO().id(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
				.message(ex.getLocalizedMessage() + " - " + builder.substring(0, builder.length() - 1));
		return new ResponseEntity<Object>(errorDTO, HttpStatus.valueOf(errorDTO.getId()));
	}

	// 422

	@ExceptionHandler({ ValidationException.class })
	protected ResponseEntity<Object> handleHttpUnprocessableEntity(final Exception ex) {
		final ErrorDTO errorDTO = new ErrorDTO().id(HttpStatus.UNPROCESSABLE_ENTITY.value())
				.message(ex.getLocalizedMessage());
		return new ResponseEntity<Object>(errorDTO, HttpStatus.valueOf(errorDTO.getId()));
	}

	// 500

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAll(final Exception ex, final WebRequest request) {
		final ErrorDTO errorDTO = new ErrorDTO().id(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.message(ex.getLocalizedMessage());
		return new ResponseEntity<Object>(errorDTO, HttpStatus.valueOf(errorDTO.getId()));
	}
}
