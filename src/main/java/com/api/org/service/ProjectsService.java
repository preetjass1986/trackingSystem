package com.api.org.service;

import org.springframework.web.multipart.MultipartFile;

import com.api.org.security.UserPrincipal;
import com.api.org.view.Response;
import com.api.org.view.Request;

public interface ProjectsService {
	
	Response project(UserPrincipal user,Long id);	
	
	Response project(UserPrincipal user);
	
	Response manageProject(UserPrincipal user,Request request);
	
	Response manageProjectBOM(UserPrincipal user,Long projectId,MultipartFile file);
	
	

	

}
