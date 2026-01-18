package com.api.org.exception;

public class NotAuthorisedException extends RuntimeException {

	
	//Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

	public NotAuthorisedException(String message) {
        super(message);
        //logger.error(message,this);
    }

    public NotAuthorisedException(String message, Throwable cause) {
        super(message, cause);
        //logger.error(message,this);
    }
	
}
