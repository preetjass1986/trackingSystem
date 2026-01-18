package com.api.org.service;

import com.api.org.model.Users;
import com.api.org.security.UserPrincipal;
import com.api.org.view.LoginRequest;
import com.api.org.view.RegisterRequest;
import com.api.org.view.Response;

public interface UserService {
	
	
	Response registerUser(RegisterRequest signUpRequest,UserPrincipal userPrincipal);
	
    Response login(LoginRequest loginRequest);    
    
    Users getUserDetailByToken(String token);

}
