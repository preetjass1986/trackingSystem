package com.api.org.service;

import com.api.org.security.UserPrincipal;
import com.api.org.view.Request;
import com.api.org.view.Response;

public interface StagesService {
	
	Response getStages(UserPrincipal user,Integer id);	
	
	Response getStages(UserPrincipal user);
	
	Response getStages(UserPrincipal user,Integer facilityId,Integer status);
	
	Response manageStages(UserPrincipal user,Request request);
	
	Response getStagesFlow(UserPrincipal user,Integer id);
	
	Response getStagesFlow(UserPrincipal user,Long projectId,Integer facilityId,Integer status);
	
	Response getStagesFlow(UserPrincipal user);
	
	Response manageStagesFlow(UserPrincipal user,Request request);

}
