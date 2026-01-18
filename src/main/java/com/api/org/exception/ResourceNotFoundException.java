package com.api.org.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	 /**
		 * 
		 */
		private static final long serialVersionUID = 5190405839831337102L;
		//Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

		public ResourceNotFoundException(String message) {
	        super(message);
	        //logger.error(message,this);
	    }
		

	    public ResourceNotFoundException(String message, Throwable cause) {
	        super(message, cause);
	        //logger.error(message,this);
	    }
}
