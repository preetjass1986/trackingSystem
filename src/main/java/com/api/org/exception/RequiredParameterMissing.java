package com.api.org.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequiredParameterMissing extends RuntimeException {

	private static final long serialVersionUID = 5190405839831337102L;
	//Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

	public RequiredParameterMissing(String message) {
        super(message);
       // logger.error(message,this);
    }

    public RequiredParameterMissing(String message, Throwable cause) {
        super(message, cause);
        //logger.error(message,this);
    }
}
