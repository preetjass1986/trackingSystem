package com.api.org.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.org.constants.AppConstants;
import com.api.org.exception.BadRequestException;
import com.api.org.exception.RequiredParameterMissing;
import com.api.org.exception.ResourceNotFoundException;
import com.api.org.model.Projects;
import com.api.org.model.SurveyResults;
import com.api.org.model.SurveySchemas;
import com.api.org.model.SurveySchemasLog;
import com.api.org.repository.ProjectsRepository;
import com.api.org.security.UserPrincipal;
import com.api.org.service.ProjectsService;
import com.api.org.service.UserService;
import com.api.org.util.CommonFunctions;
import com.api.org.view.Response;
import com.api.org.view.Request;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service("ProjectsService")
public class ProjectsServiceImpl  implements ProjectsService {
	
	Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
	@Autowired
	private ProjectsRepository projectsRepository;
	
	
	@Override
    public Response project(UserPrincipal user,Long id)
    {
		if(id!=null)
		{
			Optional<Projects> projectsOpt=projectsRepository.findById(id);		
			
			if(projectsOpt.isPresent())return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR).setData(projectsOpt.get());
			else {throw new ResourceNotFoundException(AppConstants.NOT_FOUND_STR);}
		}
		else { throw new BadRequestException(AppConstants.REQUIRED_PARAMETER_MISSING);}
			
    }
	
	@Override
	public Response project(UserPrincipal user)
	{
			Pageable pagable = PageRequest.of(0, 100);
			List<Projects>  projects=projectsRepository.getData(pagable);
			if(projects!=null && projects.size()>0)return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR).setData(projects);
			else {throw new ResourceNotFoundException(AppConstants.NOT_FOUND_STR);}
	}
	
	@Override
	public Response manageProject(UserPrincipal user,Request request)
	{		
		
			if(request!=null )
			{
				if(request.getAction()==AppConstants.ONE) // add
				{
					if(!projectsRepository.existsByName(request.getName()))
					{
						projectsRepository.save(new Projects(request.getName(),request.getId()));
						return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR);
					}
					else { throw new BadRequestException(AppConstants.RECORD_ALREDAY_EXISTS_STR);}	
				}
				else if(request.getAction()==AppConstants.TWO) // update
				{
					Optional<Projects> projectsOpt=projectsRepository.findById(request.getId());
					if(projectsOpt.isPresent())
					{
						Projects project=projectsOpt.get();
						project.setName(request.getName());
						project.setUpdatedOn(new Date());
						projectsRepository.save(project);
						return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR).setData(project);	
					}else { throw new BadRequestException(AppConstants.RECORD_NOT_FOUND);}	
				}
				else if(request.getAction()==AppConstants.THREE) // delete
				{
					Optional<Projects> projectsOpt=projectsRepository.findById(request.getId());
					if(projectsOpt.isPresent())
					{
						projectsRepository.delete(projectsOpt.get());
						return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR);	
					}else { throw new BadRequestException(AppConstants.RECORD_NOT_FOUND);}	
				}
				else { throw new BadRequestException(AppConstants.REQUIRED_PARAMETER_MISSING);}	
			}
			else { throw new BadRequestException(AppConstants.REQUIRED_PARAMETER_MISSING);}	
		
	}
	
	


}
