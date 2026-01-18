package com.api.org.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.org.constants.AppConstants;
import com.api.org.exception.BadRequestException;
import com.api.org.exception.ResourceNotFoundException;
import com.api.org.model.Components;
import com.api.org.model.Projects;
import com.api.org.repository.ComponentsRepository;
import com.api.org.repository.ProjectsRepository;
import com.api.org.security.UserPrincipal;
import com.api.org.service.ComponentsService;
import com.api.org.view.Request;
import com.api.org.view.Response;

@Service("ComponentsService")
public class ComponentsServiceImpl implements ComponentsService {

	Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
	@Autowired
	private ComponentsRepository componentsRepository;
	
	@Override
	public Response component(UserPrincipal user, Long id) {
		if(id!=null)
		{
			Optional<Components> componentsOpt=componentsRepository.findById(id);		
			
			if(componentsOpt.isPresent())return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR).setData(componentsOpt.get());
			else {throw new ResourceNotFoundException(AppConstants.NOT_FOUND_STR);}
		}
		else { throw new BadRequestException(AppConstants.REQUIRED_PARAMETER_MISSING);}
			
	}

	@Override
	public Response component(UserPrincipal user) {
		Pageable pagable = PageRequest.of(0, 100);
		List<Components>  components=componentsRepository.getData(pagable);
		if(components!=null && components.size()>0)return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR).setData(components);
		else {throw new ResourceNotFoundException(AppConstants.NOT_FOUND_STR);}
	}

	@Override
	public Response manageComponent(UserPrincipal user, Request request) {
		if(request!=null )
		{
			if(request.getAction()==AppConstants.ONE) // add
			{
				if(!componentsRepository.existsByName(request.getName()))
				{
					componentsRepository.save(new Components(request.getProjectId(),request.getModuleId(),request.getComponentsCount(),request.getName()));
					return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR);
				}
				else { throw new BadRequestException(AppConstants.RECORD_ALREDAY_EXISTS_STR);}	
			}
			else if(request.getAction()==AppConstants.TWO) // update
			{
				Optional<Components> componentsOpt=componentsRepository.findById(request.getId());
				if(componentsOpt.isPresent())
				{
					Components component=componentsOpt.get();
					if(request.getName()!=null)component.setName(request.getName());
					if(request.getComponentsCount()!=null)component.setComponentsCount(request.getComponentsCount());
					component.setUpdatedOn(new Date());
					componentsRepository.save(component);
					return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR).setData(component);	
				}else { throw new BadRequestException(AppConstants.RECORD_NOT_FOUND);}	
			}
			else if(request.getAction()==AppConstants.THREE) // delete
			{
				Optional<Components> componentsOpt=componentsRepository.findById(request.getId());
				if(componentsOpt.isPresent())
				{
					componentsRepository.delete(componentsOpt.get());
					return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR);	
				}else { throw new BadRequestException(AppConstants.RECORD_NOT_FOUND);}	
			}
			else { throw new BadRequestException(AppConstants.REQUIRED_PARAMETER_MISSING);}	
		}
		else { throw new BadRequestException(AppConstants.REQUIRED_PARAMETER_MISSING);}	
	}

}
