package com.api.org.service;

import com.api.org.security.UserPrincipal;
import com.api.org.view.Request;
import com.api.org.view.Response;

public interface ComponentsMasterService {
	
    Response componentsMaster(UserPrincipal user,Long id);	
	
	Response componentsMaster(UserPrincipal user);
		
	Response manageComponentsMaster(UserPrincipal user,Request request);

}
