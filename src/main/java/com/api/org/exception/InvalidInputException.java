package com.api.org.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidInputException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5190405839831337102L;
	//Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

	public InvalidInputException(String message) {
        super(message);
        //logger.error(message,this);
    }

    public InvalidInputException(String message, Throwable cause) {
        super(message, cause);
        //logger.error(message,this);
    }
}
