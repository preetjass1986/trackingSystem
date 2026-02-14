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
import com.api.org.model.Facilities;
import com.api.org.model.FacilitiesFlow;
import com.api.org.model.Stages;
import com.api.org.repository.FacilitiesFlowRepository;
import com.api.org.repository.FacilitiesRepository;
import com.api.org.repository.StagesFlowRepository;
import com.api.org.security.UserPrincipal;
import com.api.org.service.FacilitiesService;
import com.api.org.view.Request;
import com.api.org.view.Response;

@Service("FacilitiesService")
public class FacilitiesServiceImpl implements FacilitiesService{

	 Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
		
	 
	@Autowired
	private FacilitiesRepository facilitiesRepository;
	
	@Autowired
	private FacilitiesFlowRepository facilitiesFlowRepository;
	
	
	@Override
	public Response getFacilities(UserPrincipal user, Integer id) {
		if(id!=null)
		{
			Optional<Facilities> facilitiesOpt=facilitiesRepository.findById(id);
			if(facilitiesOpt.isPresent())return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR).setData(facilitiesOpt.get());
			else {throw new ResourceNotFoundException(AppConstants.NOT_FOUND_STR);}
		}
		else { throw new BadRequestException(AppConstants.REQUIRED_PARAMETER_MISSING);}
	}

	@Override
	public Response getFacilities(UserPrincipal user) {
		Pageable pagable = PageRequest.of(0, 100);
		List<Facilities>  facilities=facilitiesRepository.getData(pagable);
		if(facilities!=null && facilities.size()>0)return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR).setData(facilities);
		else {throw new ResourceNotFoundException(AppConstants.NOT_FOUND_STR);}
	}

	@Override
	public Response manageFacilities(UserPrincipal user, Request request) {
		if(request!=null )
		{
			if(request.getAction()==AppConstants.ONE) // add
			{
				if(!facilitiesRepository.existsByName(request.getName()))
				{
					facilitiesRepository.save(new Facilities(request.getName(),request.getCode(),user.getId()));
					return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR);
				}
				else { throw new BadRequestException(AppConstants.RECORD_ALREDAY_EXISTS_STR);}	
			}
			else if(request.getAction()==AppConstants.TWO) // update
			{
				Optional<Facilities> facilitiesOpt=facilitiesRepository.findById(request.getFacilityId());
				if(facilitiesOpt.isPresent())
				{
					Facilities facility=facilitiesOpt.get();
					if(request.getName()!=null) facility.setName(request.getName());
					if(request.getCode()!=null) facility.setCode(request.getCode());
					if(request.getStatus()!=null) facility.setStatus(request.getStatus());
					facility.setUpdatedOn(new Date());
					facilitiesRepository.save(facility);
					return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR).setData(facility);	
				}else { throw new BadRequestException(AppConstants.RECORD_NOT_FOUND);}	
			}
			else if(request.getAction()==AppConstants.THREE) // delete
			{
				Optional<Facilities> facilitiesOpt=facilitiesRepository.findById(request.getFacilityId());
				if(facilitiesOpt.isPresent())
				{
					facilitiesRepository.delete(facilitiesOpt.get());
					return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR);	
				}else { throw new BadRequestException(AppConstants.RECORD_NOT_FOUND);}	
			}
			else { throw new BadRequestException(AppConstants.REQUIRED_PARAMETER_MISSING);}	
		}
		else { throw new BadRequestException(AppConstants.REQUIRED_PARAMETER_MISSING);}	
	}

	@Override
	public Response getFacilitiesFlow(UserPrincipal user, Integer id) {
		if(id!=null)
		{
			Optional<FacilitiesFlow> facilitiesFlowOpt=facilitiesFlowRepository.findById(id);
			if(facilitiesFlowOpt.isPresent())return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR).setData(facilitiesFlowOpt.get());
			else {throw new ResourceNotFoundException(AppConstants.NOT_FOUND_STR);}
		}
		else { throw new BadRequestException(AppConstants.REQUIRED_PARAMETER_MISSING);}
	}

	@Override
	public Response getFacilitiesFlow(UserPrincipal user) {
		Pageable pagable = PageRequest.of(0, 100);
		List<FacilitiesFlow>  facilitiesFlow=facilitiesFlowRepository.getData(pagable);
		if(facilitiesFlow!=null && facilitiesFlow.size()>0)return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR).setData(facilitiesFlow);
		else {throw new ResourceNotFoundException(AppConstants.NOT_FOUND_STR);}
	}

	@Override
	public Response manageFacilitiesFlow(UserPrincipal user, Request request) {
		if(request!=null )
		{
			if(request.getAction()==AppConstants.ONE) // add
			{
				if(!facilitiesFlowRepository.existsByProjectIdAndFacilityId(request.getProjectId(),request.getFacilityId()))
				{
					facilitiesFlowRepository.save(new FacilitiesFlow(request.getProjectId(),request.getFacilityId(),request.getSequenceNo(),user.getId()));
					return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR);
				}
				else { throw new BadRequestException(AppConstants.RECORD_ALREDAY_EXISTS_STR);}	
			}
			else if(request.getAction()==AppConstants.TWO) // update
			{
				Optional<FacilitiesFlow> facilitiesFlowOpt=facilitiesFlowRepository.findById(request.getFacilityFlowId());
				if(facilitiesFlowOpt.isPresent())
				{
					FacilitiesFlow facilitiesFlow=facilitiesFlowOpt.get();
					if(request.getProjectId()!=null) facilitiesFlow.setProjectId(request.getProjectId());
					if(request.getFacilityId()!=null) facilitiesFlow.setFacilityId(request.getFacilityId());
					if(request.getSequenceNo()!=null) facilitiesFlow.setSequenceNo(request.getSequenceNo());
					if(request.getStatus()!=null) facilitiesFlow.setStatus(request.getStatus());
					facilitiesFlow.setUpdatedOn(new Date());
					facilitiesFlowRepository.save(facilitiesFlow);
					return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR).setData(facilitiesFlow);	
				}else { throw new BadRequestException(AppConstants.RECORD_NOT_FOUND);}	
			}
			else if(request.getAction()==AppConstants.THREE) // delete
			{
				Optional<Facilities> facilitiesOpt=facilitiesRepository.findById(request.getFacilityId());
				if(facilitiesOpt.isPresent())
				{
					facilitiesRepository.delete(facilitiesOpt.get());
					return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR);	
				}else { throw new BadRequestException(AppConstants.RECORD_NOT_FOUND);}	
			}
			else { throw new BadRequestException(AppConstants.REQUIRED_PARAMETER_MISSING);}	
		}
		else { throw new BadRequestException(AppConstants.REQUIRED_PARAMETER_MISSING);}	
	}

}
