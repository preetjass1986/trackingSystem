package com.api.org.serviceImpl;

import org.springframework.stereotype.Service;

import com.api.org.security.UserPrincipal;
import com.api.org.service.ComponentsService;
import com.api.org.view.Request;
import com.api.org.view.Response;

@Service("ComponentsService")
public class ComponentsServiceImpl implements ComponentsService {

	@Override
	public Response component(UserPrincipal user, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response component(UserPrincipal user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response manageComponent(UserPrincipal user, Request surveyRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
