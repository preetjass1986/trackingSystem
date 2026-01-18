package com.api.org.exception;

import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;

import com.api.org.constants.AppConstants;
import com.api.org.view.Response;

@ControllerAdvice
@Component
public class ServerExceptionHandling {

	Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

	//@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	/*@ExceptionHandler(HttpMessageNotReadableException.class)
	public @ResponseBody Response handleException(final Exception e, final HttpServletRequest request) {
		logger.error("[ERROR-> handleException()] " + e.getMessage());
		e.printStackTrace();
		return new Response().setResponseCode(AppConstants.REQUEST_FAIL).setMessage(AppConstants.REQUEST_FAIL_STR);
	}*/

	@ExceptionHandler({ MaxUploadSizeExceededException.class, MultipartException.class })
	public ResponseEntity<Response> MaxUploadSizeExceededException(Exception e, HttpServletRequest request) {
		log(e.getMessage(), e.getCause());
		Response response = new Response().setResponseCode(AppConstants.BAD_REQUEST).setMessage(AppConstants.REQUEST_FAIL_STR+" :- "+e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<Response>  BadCredentialsException(Exception e, HttpServletRequest request) {
		log(e.getMessage(), e.getCause());	
		Response response =  new Response().setResponseCode(AppConstants.BAD_REQUEST).setMessage(AppConstants.MSG_INVALID_DETAIL);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response> MethodNotSupportedException(final Exception e, final HttpServletRequest request) {
		log(e.getMessage(), e.getCause());
		Response response = new Response().setResponseCode(AppConstants.BAD_REQUEST).setMessage(AppConstants.REQUEST_FAIL_STR+" :- "+e.getMessage());
		return ResponseEntity.status(HttpStatus. BAD_REQUEST).body(response);
	}

	@ExceptionHandler(value = java.util.NoSuchElementException.class)
	public ResponseEntity<Response> exceptionResponse(NoSuchElementException e) {
		log(e.getMessage(), e.getCause());
		Response response = new Response().setResponseCode(AppConstants.NOT_FOUND).setMessage(e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler(value = InvalidInputException.class)
	public ResponseEntity<Response> invalidInputExceptionHandler(InvalidInputException e) {
		log(e.getMessage(), e.getCause());
		Response response = new Response().setResponseCode(AppConstants.BAD_REQUEST).setMessage(e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	public ResponseEntity<Response> messageNotReadableException(HttpMessageNotReadableException e, WebRequest w) {
		log(e.getMessage(), e.getCause());
		w.getDescription(false);
		Response response = new Response().setResponseCode(AppConstants.BAD_REQUEST).setMessage(AppConstants.REQUIRED_BODY_MISSING);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler(value = java.lang.NumberFormatException.class)
	public ResponseEntity<Response> numberFormatException(NumberFormatException e) {
		log(e.getMessage(), e.getCause());
		Response response = new Response().setResponseCode(AppConstants.BAD_REQUEST).setMessage(e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Response> dataIntegrityException(DataIntegrityViolationException e, WebRequest w) {
		log(e.getMessage(), e.getCause());
		Response response = new Response().setResponseCode(AppConstants.BAD_REQUEST).setMessage(AppConstants.REQUIRED_PARAMETER_MISSING);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<Response> validAnnotationExceptionHandler(MethodArgumentNotValidException e, WebRequest w) {
		log(e.getMessage(), e.getCause());
		Response response = new Response().setResponseCode(AppConstants.BAD_REQUEST).setMessage(AppConstants.INVALID_INPUT_OR_VALUE);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler(value = AppException.class)
	public ResponseEntity<Response> applicationExceptionHandler(AppException e, WebRequest w) {
		log(e.getMessage(), e.getCause());
		w.getDescription(false);
		Response response = new Response().setResponseCode(AppConstants.BAD_REQUEST).setMessage(e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler(value = IllegalArgumentException.class)
	public ResponseEntity<Response> illegalArgumentException(IllegalArgumentException e, WebRequest w) {
		log(e.getMessage(), e.getCause());
		w.getDescription(false);
		Response response = new Response().setResponseCode(AppConstants.BAD_REQUEST).setMessage(e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	
	@ExceptionHandler(value = BadRequestException.class)
	public ResponseEntity<Response> badRequestException(BadRequestException e, WebRequest w) {
		log(e.getMessage(), e.getCause());
		w.getDescription(false);
		Response response = new Response().setResponseCode(AppConstants.BAD_REQUEST).setMessage(e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}	
	
	@ExceptionHandler(value = RequiredParameterMissing.class)
	public ResponseEntity<Response> RequiredParameterMissing(RequiredParameterMissing e, WebRequest w) {
		log("RequiredParameterMissing="+e.getMessage(), e.getCause());
		w.getDescription(false);
		Response response = new Response().setMessage(e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}	

	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<Response> resourceNotFoundException(ResourceNotFoundException e, WebRequest w) {
		log(e.getMessage(), e.getCause());
		w.getDescription(false);
		Response response = new Response().setMessage(e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	
	@ExceptionHandler(value = AlreadyExistsException.class)
	public ResponseEntity<Response> alreadyExistsException(AlreadyExistsException e, WebRequest w) {
		log(e.getMessage(), e.getCause());
		w.getDescription(false);
		Response response = new Response().setMessage(e.getMessage());
		return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(response);
	}
	
	
	@ExceptionHandler(value = NotAuthorisedException.class)
	public ResponseEntity<Response> RequiredParameterMissing(NotAuthorisedException e, WebRequest w) {
		log(e.getMessage(), e.getCause());
		w.getDescription(false);
		Response response = new Response().setMessage(e.getMessage());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
	}
	
	@ExceptionHandler(value = InternalServerException.class)
	public ResponseEntity<Response> internalServerException(InternalServerException e, WebRequest w) {
		log(e.getMessage(), e.getCause());
		w.getDescription(false);
		Response response = new Response().setMessage(e.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	
	@ExceptionHandler(value = CallbackException.class)
	public ResponseEntity<Response> CallbackException(CallbackException e, WebRequest w) {
		log("CallbackException="+e.getMessage(), e.getCause());
		w.getDescription(false);
	    HttpHeaders responseHeaders = new HttpHeaders();
		Response response = new Response().setMessage(e.getMessage());
		return ResponseEntity.status(HttpStatus.CREATED).headers(responseHeaders).body(response);
	}
	private void log(String message, Throwable code)
	{
		logger.error(message,code);
	}
}
