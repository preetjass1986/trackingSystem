package com.api.org.controller;



import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.api.org.constants.AppConstants;
import com.api.org.exception.AppException;
import com.api.org.exception.InvalidInputException;
import com.api.org.exception.NotAuthorisedException;
import com.api.org.exception.RequiredParameterMissing;
import com.api.org.security.CurrentUser;
import com.api.org.security.UserPrincipal;
import com.api.org.service.UserService;
import com.api.org.view.JwtAuthenticationResponse;
import com.api.org.view.LoginRequest;
import com.api.org.view.RegisterRequest;
import com.api.org.view.Response;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;


@RestController
@RequestMapping(AppConstants.CONTROLLER_MAIN_API)
public class OpenController {
	
	
	@Autowired
	private UserService userService;
	
	//@SecurityRequirements(value = {})
	@PostMapping(AppConstants.CONTROLLER_REGISTER_USER)	
	@Operation(summary = "Register user", description = "")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "{\"message\": \"Success\"}"), 
	        @ApiResponse(responseCode = "208", description = "{\"message\": \"Record already exist.\"}"),
	        @ApiResponse(responseCode = "400", description = "{\"message\": \"Required parameter missing.\"}")
	    })
	public Response createUser(@Valid @RequestBody RegisterRequest signUpRequest,
			@Parameter(hidden = true) @CurrentUser UserPrincipal userPrincipal) {
		return userService.registerUser(signUpRequest,userPrincipal);
	}
	
	@SecurityRequirements(value = {})
	@PostMapping(AppConstants.CONTROLLER_LOGIN)
	@Operation(summary = "Login request", description = "Return a access token to access other authorized APIs")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "{\"message\": \"Success\",\"data\": { \"accessToken\": \"eyJhbGciO**************\",\"tokenType\": \"Bearer\",\"name\": \"S Admin\"}}"), 
	        @ApiResponse(responseCode = "404", description = "{\"message\": \"Invalid username or password.\"}")
	    })
	public Response login(@Valid @RequestBody LoginRequest loginRequest) 
	{				
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		System.out.println(encoder.encode("admin123"));

		return userService.login(loginRequest);
	}
	

	

}
