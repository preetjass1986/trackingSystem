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
import com.api.org.model.ComponentsMaster;
import com.api.org.repository.ComponentsMasterRepository;
import com.api.org.repository.ComponentsRepository;
import com.api.org.security.UserPrincipal;
import com.api.org.service.ComponentsMasterService;
import com.api.org.view.Request;
import com.api.org.view.Response;

@Service("ComponentsMasterService")
public class ComponentsMasterServiceImpl implements ComponentsMasterService{

Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
	@Autowired
	private ComponentsMasterRepository componentsMasterRepository;
	
	@Override
	public Response componentsMaster(UserPrincipal user, Long id) {
		if(id!=null)
		{
			Optional<ComponentsMaster> componentMastersOpt=componentsMasterRepository.findById(id);		
			
			if(componentMastersOpt.isPresent())return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR).setData(componentMastersOpt.get());
			else {throw new ResourceNotFoundException(AppConstants.NOT_FOUND_STR);}
		}
		else { throw new BadRequestException(AppConstants.REQUIRED_PARAMETER_MISSING);}
	}

	@Override
	public Response componentsMaster(UserPrincipal user) {
		Pageable pagable = PageRequest.of(0, 100);
		List<ComponentsMaster>  componentsMaster=componentsMasterRepository.getData(pagable);
		if(componentsMaster!=null && componentsMaster.size()>0)return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR).setData(componentsMaster);
		else {throw new ResourceNotFoundException(AppConstants.NOT_FOUND_STR);}
	}

	@Override
	public Response manageComponentsMaster(UserPrincipal user, Request request) {
		if(request!=null )
		{
			if(request.getAction()==AppConstants.ONE) // add
			{
				if(!componentsMasterRepository.existsByName(request.getName()))
				{
					componentsMasterRepository.save(new ComponentsMaster(request.getName()));
					return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR);
				}
				else { throw new BadRequestException(AppConstants.RECORD_ALREDAY_EXISTS_STR);}	
			}
			else if(request.getAction()==AppConstants.TWO) // update
			{
				Optional<ComponentsMaster> componentsMasterOpt=componentsMasterRepository.findById(request.getId());
				if(componentsMasterOpt.isPresent())
				{
					ComponentsMaster componentsMaster=componentsMasterOpt.get();
					if(request.getName()!=null)componentsMaster.setName(request.getName());
					componentsMaster.setUpdatedOn(new Date());
					componentsMasterRepository.save(componentsMaster);
					return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR).setData(componentsMaster);	
				}else { throw new BadRequestException(AppConstants.RECORD_NOT_FOUND);}	
			}
			else if(request.getAction()==AppConstants.THREE) // delete
			{
				Optional<ComponentsMaster> componentsMasterOpt=componentsMasterRepository.findById(request.getId());
				if(componentsMasterOpt.isPresent())
				{
					componentsMasterRepository.delete(componentsMasterOpt.get());
					return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR);	
				}else { throw new BadRequestException(AppConstants.RECORD_NOT_FOUND);}	
			}
			else { throw new BadRequestException(AppConstants.REQUIRED_PARAMETER_MISSING);}	
		}
		else { throw new BadRequestException(AppConstants.REQUIRED_PARAMETER_MISSING);}
	}
	

}
