package com.api.org.service;

import com.api.org.security.UserPrincipal;
import com.api.org.view.Response;
import com.api.org.view.Request;

public interface ProjectsService {
	
	Response project(UserPrincipal user,Long id);	
	
	Response project(UserPrincipal user);
	
	Response manageProject(UserPrincipal user,Request surveyRequest);
	

	
	

	

}
