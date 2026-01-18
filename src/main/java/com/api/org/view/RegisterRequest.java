package com.api.org.view;

import javax.validation.constraints.*;
import lombok.Data;

@Data
public class RegisterRequest {

	@NotBlank
	@Size(max = 10)
	private String username;
	
	@NotBlank
	@Size(max = 50)
	private String password;	

	@NotBlank
	@Size(max = 50)
	private String name;

	@Size(max = 40)
	@Email
	private String email;

	
	public RegisterRequest() {
	}
	
	


}
