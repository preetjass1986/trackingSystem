package com.api.org.service;

import com.api.org.security.UserPrincipal;
import com.api.org.view.Request;
import com.api.org.view.Response;

public interface ComponentsService {
	
	Response component(UserPrincipal user,Long id);	
	
	Response component(UserPrincipal user);
		
	Response manageComponent(UserPrincipal user,Request surveyRequest);

}
