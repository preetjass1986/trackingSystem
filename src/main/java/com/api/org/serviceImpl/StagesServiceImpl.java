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
import com.api.org.model.Stages;
import com.api.org.repository.StagesRepository;
import com.api.org.model.StagesFlow;
import com.api.org.repository.StagesFlowRepository;
import com.api.org.security.UserPrincipal;
import com.api.org.service.StagesService;
import com.api.org.view.Request;
import com.api.org.view.Response;

@Service("StagesService")
public class StagesServiceImpl implements StagesService{

   Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
	@Autowired
	private StagesRepository stagesRepository;
	
	@Autowired
	private StagesFlowRepository stagesFlowRepository;
	
	@Override
	public Response getStages(UserPrincipal user, Integer id) {
		if(id!=null)
		{
			Optional<Stages> stagesOpt=stagesRepository.findById(id);		
			
			if(stagesOpt.isPresent())return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR).setData(stagesOpt.get());
			else {throw new ResourceNotFoundException(AppConstants.NOT_FOUND_STR);}
		}
		else { throw new BadRequestException(AppConstants.REQUIRED_PARAMETER_MISSING);}
	}
	
	@Override
	public Response getStages(UserPrincipal user) {
		Pageable pagable = PageRequest.of(0, 100);
		List<Stages>  stages=stagesRepository.getData(pagable);
		if(stages!=null && stages.size()>0)return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR).setData(stages);
		else {throw new ResourceNotFoundException(AppConstants.NOT_FOUND_STR);}
	}
	
	@Override
	public Response getStages(UserPrincipal user,Integer facilityId,Integer status) {
		Pageable pagable = PageRequest.of(0, 100);
		List<Stages>  stages=stagesRepository.getData(pagable);
		if(stages!=null && stages.size()>0)return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR).setData(stages);
		else {throw new ResourceNotFoundException(AppConstants.NOT_FOUND_STR);}
	}

	@Override
	public Response manageStages(UserPrincipal user, Request request) {
		if(request!=null )
		{
			if(request.getAction()==AppConstants.ONE) // add
			{
				if(!stagesRepository.existsByName(request.getName()))
				{
					stagesRepository.save(new Stages(request.getName(),request.getCode(),request.getFacilityId(),request.getSequenceNo(),user.getId()));
					return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR);
				}
				else { throw new BadRequestException(AppConstants.RECORD_ALREDAY_EXISTS_STR);}	
			}
			else if(request.getAction()==AppConstants.TWO) // update
			{
				Optional<Stages> stagesOpt=stagesRepository.findById(request.getStageId());
				if(stagesOpt.isPresent())
				{
					Stages stage=stagesOpt.get();
					if(request.getName()!=null) stage.setName(request.getName());
					if(request.getCode()!=null) stage.setCode(request.getCode());
					if(request.getFacilityId()!=null) stage.setFacilityId(request.getFacilityId());
					if(request.getSequenceNo()!=null) stage.setSequenceNo(request.getSequenceNo());
					if(request.getStatus()!=null) stage.setStatus(request.getStatus());
					stage.setUpdatedOn(new Date());
					stagesRepository.save(stage);
					return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR).setData(stage);	
				}else { throw new BadRequestException(AppConstants.RECORD_NOT_FOUND);}	
			}
			else if(request.getAction()==AppConstants.THREE) // delete
			{
				Optional<Stages> stagesOpt=stagesRepository.findById(request.getStageId());
				if(stagesOpt.isPresent())
				{
					stagesRepository.delete(stagesOpt.get());
					return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR);	
				}else { throw new BadRequestException(AppConstants.RECORD_NOT_FOUND);}	
			}
			else { throw new BadRequestException(AppConstants.REQUIRED_PARAMETER_MISSING);}	
		}
		else { throw new BadRequestException(AppConstants.REQUIRED_PARAMETER_MISSING);}	
	}

	@Override
	public Response getStagesFlow(UserPrincipal user, Integer id) {
		if(id!=null)
		{
			Optional<StagesFlow> stagesFlowOpt=stagesFlowRepository.findById(id);
			if(stagesFlowOpt.isPresent())return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR).setData(stagesFlowOpt.get());
			else {throw new ResourceNotFoundException(AppConstants.NOT_FOUND_STR);}
		}
		else { throw new BadRequestException(AppConstants.REQUIRED_PARAMETER_MISSING);}
	}
	
	@Override
	public Response getStagesFlow(UserPrincipal user,Long projectId,Integer facilityId,Integer Status) {
		Pageable pagable = PageRequest.of(0, 100);
		List<StagesFlow>  stagesFlow=stagesFlowRepository.getData(pagable);
		if(stagesFlow!=null && stagesFlow.size()>0)return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR).setData(stagesFlow);
		else {throw new ResourceNotFoundException(AppConstants.NOT_FOUND_STR);}
	}

	@Override
	public Response getStagesFlow(UserPrincipal user) {
		Pageable pagable = PageRequest.of(0, 100);
		List<StagesFlow>  stagesFlow=stagesFlowRepository.getData(pagable);
		if(stagesFlow!=null && stagesFlow.size()>0)return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR).setData(stagesFlow);
		else {throw new ResourceNotFoundException(AppConstants.NOT_FOUND_STR);}
	}

	@Override
	public Response manageStagesFlow(UserPrincipal user, Request request) {
		if(request!=null )
		{
			if(request.getAction()==AppConstants.ONE) // add
			{
				if(!stagesFlowRepository.existsByProjectIdAndFacilityIdAndStageId(request.getProjectId(),request.getFacilityId(),request.getStageId()))
				{
					stagesFlowRepository.save(new StagesFlow(request.getProjectId(),request.getFacilityId(),request.getStageId(),request.getSequenceNo(),user.getId()));
					return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR);
				}
				else { throw new BadRequestException(AppConstants.RECORD_ALREDAY_EXISTS_STR);}	
			}
			else if(request.getAction()==AppConstants.TWO) // update
			{
				Optional<StagesFlow> stagesFlowOpt=stagesFlowRepository.findById(request.getStageFlowId());
				if(stagesFlowOpt.isPresent())
				{
					StagesFlow stagesFlow=stagesFlowOpt.get();
					if(request.getProjectId()!=null) stagesFlow.setProjectId(request.getProjectId());
					if(request.getFacilityId()!=null) stagesFlow.setFacilityId(request.getFacilityId());
					if(request.getStageId()!=null) stagesFlow.setStageId(request.getStageId());
					if(request.getSequenceNo()!=null) stagesFlow.setSequenceNo(request.getSequenceNo());
					if(request.getStatus()!=null) stagesFlow.setStatus(request.getStatus());
					stagesFlow.setUpdatedOn(new Date());
					stagesFlowRepository.save(stagesFlow);
					return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR).setData(stagesFlow);	
				}else { throw new BadRequestException(AppConstants.RECORD_NOT_FOUND);}	
			}
			else if(request.getAction()==AppConstants.THREE) // delete
			{
				Optional<StagesFlow> stagesFlowOpt=stagesFlowRepository.findById(request.getStageFlowId());
				if(stagesFlowOpt.isPresent())
				{
					stagesFlowRepository.delete(stagesFlowOpt.get());
					return new Response().setResponseCode(AppConstants.SUCCESS).setMessage(AppConstants.SUCCESS_STR);	
				}else { throw new BadRequestException(AppConstants.RECORD_NOT_FOUND);}	
			}
			else { throw new BadRequestException(AppConstants.REQUIRED_PARAMETER_MISSING);}	
		}
		else { throw new BadRequestException(AppConstants.REQUIRED_PARAMETER_MISSING);}	
	}

}
