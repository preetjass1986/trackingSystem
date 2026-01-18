package com.api.org.view;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Request {
	 Long id;
	 Integer action;
	 Long projectId;		
	 Long moduleId;
	 Integer componentsCount;
	 
	 @NotBlank
	 String content;
	 
	 String token;
	 
	 @NotBlank
	 @Size(max = 50)
	 String name;
	 
	 public Request() {}
	 
	 
	 public Request(String token) {
		 this.token=token;
	 }
	 
	 public Request(Long id) {
		 this.id=id;
	 }
	 
	 

}
