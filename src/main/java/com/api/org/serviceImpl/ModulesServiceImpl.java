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
import com.api.org.model.Modules;
import com.api.org.model.Projects;
import com.api.org.repository.ModulesRepository;
import com.api.org.security.UserPrincipal;
import com.api.org.service.ModulesService;
import com.api.org.view.Request;
import com.api.org.view.Response;

@Service("ModulesService")
public class ModulesServiceImpl implements ModulesService{

	
Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
	@Autowired
	private ModulesRepository modulesRepository;
	
	@Override
	public Response module(UserPrincipal user, Long id) {
		if(id!=null)
		{
			Optional<Modules> modulesOpt=modulesRepository.findById(id);		
			
			if(modulesOpt.isPresent())return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR).setData(modulesOpt.get());
			else {throw new ResourceNotFoundException(AppConstants.NOT_FOUND_STR);}
		}else { throw new BadRequestException(AppConstants.REQUIRED_PARAMETER_MISSING);}
	}

	@Override
	public Response module(UserPrincipal user) {
		Pageable pagable = PageRequest.of(0, 100);
		List<Modules>  modules=modulesRepository.getData(pagable);
		if(modules!=null && modules.size()>0)return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR).setData(modules);
		else {throw new ResourceNotFoundException(AppConstants.NOT_FOUND_STR);}
	}

	@Override
	public Response manageModule(UserPrincipal user, Request request) {
		if(request!=null )
		{
			if(request.getAction()==AppConstants.ONE) // add
			{
				if(!modulesRepository.existsByName(request.getName()))
				{
					modulesRepository.save(new Modules(request.getName(),user.getId()));
					return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR);
				}
				else { throw new BadRequestException(AppConstants.RECORD_ALREDAY_EXISTS_STR);}	
			}
			else if(request.getAction()==AppConstants.TWO) // update
			{
				Optional<Modules> modulesOpt=modulesRepository.findById(request.getId());
				if(modulesOpt.isPresent())
				{
					Modules module=modulesOpt.get();
					if(request.getName()!=null) module.setName(request.getName());
					module.setUpdatedOn(new Date());
					modulesRepository.save(module);
					return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR).setData(module);	
				}else { throw new BadRequestException(AppConstants.RECORD_NOT_FOUND);}	
			}
			else if(request.getAction()==AppConstants.THREE) // delete
			{
				Optional<Modules> modulesOpt=modulesRepository.findById(request.getId());
				if(modulesOpt.isPresent())
				{
					modulesRepository.delete(modulesOpt.get());
					return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR);	
				}else { throw new BadRequestException(AppConstants.RECORD_NOT_FOUND);}	
			}
			else { throw new BadRequestException(AppConstants.REQUIRED_PARAMETER_MISSING);}	
		}
		else { throw new BadRequestException(AppConstants.REQUIRED_PARAMETER_MISSING);}	
	}

}
