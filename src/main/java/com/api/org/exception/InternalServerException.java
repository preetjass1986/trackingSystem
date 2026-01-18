package com.api.org.exception;

public class InternalServerException extends RuntimeException {
	
	private static final long serialVersionUID = 5190405839831337102L;

	public InternalServerException(String message) {
        super(message);
    }

    public InternalServerException(String message, Throwable cause) {
        super(message, cause);
    }


}
