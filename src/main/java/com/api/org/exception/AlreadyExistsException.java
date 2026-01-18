package com.api.org.exception;

public class AlreadyExistsException extends RuntimeException {
	
	
	public AlreadyExistsException(String message) {
        super(message);
        //logger.error(message,this);
    }

    public AlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

}
