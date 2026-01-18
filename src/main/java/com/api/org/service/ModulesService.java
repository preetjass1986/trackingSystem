package com.api.org.service;

import com.api.org.security.UserPrincipal;
import com.api.org.view.Request;
import com.api.org.view.Response;

public interface ModulesService {
	
	
    Response module(UserPrincipal user,Long id);	
	
	Response module(UserPrincipal user);
	
	Response manageModule(UserPrincipal user,Request request);

}
