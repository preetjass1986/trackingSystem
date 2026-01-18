package com.api.org.exception;


public class CallbackException extends RuntimeException {
	

	private static final long serialVersionUID = 5190405839831337102L;

	public CallbackException(String message) {
        super(message);
    }

    public CallbackException(String message, Throwable cause) {
        super(message, cause);
    }
}

