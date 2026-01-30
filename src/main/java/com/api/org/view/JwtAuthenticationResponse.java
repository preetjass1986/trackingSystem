package com.api.org.view;

import java.util.List;
import java.util.Set;

import com.api.org.constants.AppConstants;
import com.api.org.model.Roles;
import com.api.org.security.UserPrincipal;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class JwtAuthenticationResponse {
	private String accessToken;
	private String tokenType = AppConstants.BEARER;
	private String name;
	private List<Roles> roles;
	private String username;
	private Integer role;
	private String  ani;
	private Long id;
	private Integer status;

	public JwtAuthenticationResponse() {

	}
	/*
	 * public JwtAuthenticationResponse(String accessToken,String name,Integer
	 * roles) { this.accessToken=accessToken; this.name=name; this.roles=roles; }
	 */

	public JwtAuthenticationResponse(String accessToken, UserPrincipal principal, List<Roles> roles,Integer status) 
	{
		this.accessToken = accessToken;
		this.name = principal.getUsername();
		//this.circle=principal.getCircle() ;
		//this.age= principal.getAge();
		//this.gender= principal.getGender();
		//this.amount=principal.getAmount() ;
		//this.roles = roles;
		this.ani=principal.getAni();
		this.id=principal.getId();
		this.status=status;
	}
	
	public JwtAuthenticationResponse(String accessToken, UserPrincipal principal) 
	{
		this.accessToken = accessToken;
		this.name = principal.getName();
		this.username = principal.getUsername();
		this.role=principal.getRole();
		this.ani=principal.getAni();
		this.id=principal.getId();
		this.status=principal.getStatus();
	}
	

	public JwtAuthenticationResponse(String accessToken, String name,Integer status) {
		this.accessToken = accessToken;
		this.name = name;
		this.status=status;
	}

}
