package com.api.org.view;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class LoginRequest {

	
	@NotBlank
	@Size(max = 10)
    private String username;
    
    @NotBlank
    @Size(max = 50)
    private String password;
    
    
    
    public LoginRequest() {
    	
    }

	public LoginRequest(String username, @NotBlank String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public LoginRequest(Long username, @NotBlank String password) {
		super();
		this.username = String.valueOf(username);
		this.password = password;
	}

}
