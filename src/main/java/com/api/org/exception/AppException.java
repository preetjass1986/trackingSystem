package com.api.org.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Data;
import lombok.EqualsAndHashCode;

//@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
@Data
@EqualsAndHashCode(callSuper=false)
public class AppException extends RuntimeException {
	
	private static final long serialVersionUID = 8799518488180428275L;

	private String strNewErrorCode = "";
	private String appErrorMessage;

	public String getAppErrorMessage() {
		return appErrorMessage;
	}
	public void setAppErrorMessage(String appErrorMessage) {
		this.appErrorMessage = appErrorMessage;
	}

	public AppException(String strException) {
		super(strException);
	}

	public AppException(String strException,String appErrorMessage) {
		super(strException);
		this.appErrorMessage = appErrorMessage;
	}

	public AppException(String strException, Throwable t) {
		super(t);
		strNewErrorCode = strException;
	}
}
