package com.api.org.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ValidatorException extends ResponseEntityExceptionHandler {

	protected ResponseEntity<Object> handleException(MethodArgumentNotValidException ex ,HttpHeaders d){
		
		Map<String, String> errors=new HashMap<String, String>();
		
		ex.getBindingResult().getAllErrors().forEach(error ->{
			
			String fieldName=((FieldError)error).getField();
			String message =error.getDefaultMessage();
			errors.put(fieldName,message );
		});
		
		return new ResponseEntity<Object>(errors,HttpStatus.BAD_REQUEST);
		
	}
}
