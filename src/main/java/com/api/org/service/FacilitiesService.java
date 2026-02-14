package com.api.org.service;

import com.api.org.security.UserPrincipal;
import com.api.org.view.Request;
import com.api.org.view.Response;

public interface FacilitiesService {
	
	Response getFacilities(UserPrincipal user,Integer id);	
	
	Response getFacilities(UserPrincipal user);
	
	Response manageFacilities(UserPrincipal user,Request request);
	
    Response getFacilitiesFlow(UserPrincipal user,Integer id);	
	
	Response getFacilitiesFlow(UserPrincipal user);
	
	Response manageFacilitiesFlow(UserPrincipal user,Request request);


}
