package com.api.org.view;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.ToString;


@ToString
@JsonInclude(Include.NON_NULL)
public class Response {
	@JsonIgnore
	private int responseCode;
	private String message;
	private Object data;
	
	public int getResponseCode() {
		return responseCode;
	}
	public Response setResponseCode(int responseCode) {
		this.responseCode = responseCode;
		return this;
	}
	public String getMessage() {
		return message;
	}
	public Response setMessage(String message) {
		this.message = message;
		return this;
	}
	public Object getData() {
		return data;
	}
	public Response setData(Object data) {
		this.data = data;
		return this;
	}

	
	
}
