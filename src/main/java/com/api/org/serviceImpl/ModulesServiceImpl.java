package com.api.org.serviceImpl;

import org.springframework.stereotype.Service;

import com.api.org.security.UserPrincipal;
import com.api.org.service.ModulesService;
import com.api.org.view.Request;
import com.api.org.view.Response;

@Service("ModulesService")
public class ModulesServiceImpl implements ModulesService{

	@Override
	public Response module(UserPrincipal user, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response module(UserPrincipal user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response manageModule(UserPrincipal user, Request surveyRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
