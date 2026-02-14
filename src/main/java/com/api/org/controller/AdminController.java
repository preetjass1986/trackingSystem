package com.api.org.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.org.constants.AppConstants;
import com.api.org.exception.BadRequestException;
import com.api.org.exception.NotAuthorisedException;
import com.api.org.security.CurrentUser;
import com.api.org.security.UserPrincipal;
import com.api.org.service.ComponentsMasterService;
import com.api.org.service.ComponentsService;
import com.api.org.service.FacilitiesService;
import com.api.org.service.ModulesService;
import com.api.org.service.ProjectsService;
import com.api.org.service.StagesService;
import com.api.org.util.CommonApiResponses;
import com.api.org.util.CommonFunctions;
import com.api.org.view.Response;
import com.api.org.view.Request;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;

@RestController
@RequestMapping(AppConstants.CONTROLLER_MAIN_ADMIN)
public class AdminController {

	@Autowired
	private ProjectsService projectsService;
	
	@Autowired
	private ComponentsService componentsService;
	
	@Autowired
	private ComponentsMasterService componentMasterService;
	
	@Autowired
	private ModulesService modulesService;
	
	@Autowired
	private StagesService stagesService;
	
	@Autowired
	private FacilitiesService facilitiesService;
	
	
	
	
	
	
	
	@PostMapping(AppConstants.CONTROLLER_MANAGE_PROJECT)	
	@Operation(summary = "Add/Update/Delete project record", description = "It will perform action(1=add,2=update,3=delete) as per action parameter value}")
	@CommonApiResponses
	public Response manageProject(@RequestBody Request request,@Parameter(hidden = true) @CurrentUser UserPrincipal user) 
	{	
		if(CommonFunctions.hasRole(user.getRole()))
		{
			return projectsService.manageProject(user,request);
		}
		else { throw new NotAuthorisedException(AppConstants.NOT_AUTHORISED_STRING);}
	}

	
	@PostMapping(value=AppConstants.CONTROLLER_MANAGE_PROJECT_BOM, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)	
	@Operation( summary = "Upload Project BOM", description = "Uploads Project BOM Excel and saves data into BOM tables")
	@CommonApiResponses
	public Response manageProjectBOM( @CurrentUser UserPrincipal user,@RequestParam("projectId") Long projectId,@RequestParam("file") MultipartFile file) {
		if(CommonFunctions.hasRole(user.getRole())) //change this condition later to restrict for PMG Admin only
		{
		return projectsService.manageProjectBOM(user, projectId, file);
		}
		else
		{
			 throw new NotAuthorisedException(AppConstants.NOT_AUTHORISED_STRING);
		}
			
	}
	
	@GetMapping(AppConstants.CONTROLLER_PROJECT_BY_ID)
	@Operation(summary = "Get project record by id ", description = "Return a project detail as per the id")
	@CommonApiResponses
	public Response projectById(@PathVariable Long id,@Parameter(hidden = true) @CurrentUser UserPrincipal user)
	{
		if(CommonFunctions.hasRole(user.getRole()))return projectsService.project(user,id);
		else { throw new NotAuthorisedException(AppConstants.NOT_AUTHORISED_STRING);}
	}

	
	@GetMapping(AppConstants.CONTROLLER_PROJECT)
	@Operation(summary = "Get project records order by latest created", description = "Return a list of projects")
	@CommonApiResponses
	public Response projectList(@Parameter(hidden = true) @CurrentUser UserPrincipal user) 
	{
		if(CommonFunctions.hasRole(user.getRole()))return projectsService.project(user);
		else { throw new NotAuthorisedException(AppConstants.NOT_AUTHORISED_STRING);}
	}
	
	
	@PostMapping(AppConstants.CONTROLLER_MANAGE_MODULE)	
	@Operation(summary = "Add/Update/Delete module record", description = "It will perform action(1=add,2=update,3=delete) as per action parameter value}")
	@CommonApiResponses
	public Response manageModule(@RequestBody Request request,@Parameter(hidden = true) @CurrentUser UserPrincipal user) 
	{	
		if(CommonFunctions.hasRole(user.getRole()))return modulesService.manageModule(user,request);
		else { throw new NotAuthorisedException(AppConstants.NOT_AUTHORISED_STRING);}
	}	

	@GetMapping(AppConstants.CONTROLLER_MODULE_BY_ID)
	@Operation(summary = "Get module by id ", description = "Return a module detail as per the id")
	@CommonApiResponses
	public Response moduleById(@PathVariable Long id,@Parameter(hidden = true) @CurrentUser UserPrincipal user)
	{
		if(CommonFunctions.hasRole(user.getRole()))return modulesService.module(user,id);
		else { throw new NotAuthorisedException(AppConstants.NOT_AUTHORISED_STRING);}
	}

	
	@GetMapping(AppConstants.CONTROLLER_MODULE)
	@Operation(summary = "Get module records order by latest created", description = "Return a list of projects")
	@CommonApiResponses
	public Response modulesList(@Parameter(hidden = true) @CurrentUser UserPrincipal user) 
	{
		if(CommonFunctions.hasRole(user.getRole())) return modulesService.module(user);
		else { throw new NotAuthorisedException(AppConstants.NOT_AUTHORISED_STRING);}
	}
	
	
	
	
	@PostMapping(AppConstants.CONTROLLER_MANAGE_COMPONENT)	
	@Operation(summary = "Add/Update/Delete component record", description = "It will perform action(1=add,2=update,3=delete) as per action parameter value}")
	@CommonApiResponses
	public Response manageComponent(@RequestBody Request request,@Parameter(hidden = true) @CurrentUser UserPrincipal user) 
	{	
		if(CommonFunctions.hasRole(user.getRole()))return componentsService.manageComponent(user,request);
		else { throw new NotAuthorisedException(AppConstants.NOT_AUTHORISED_STRING);}
	}	

	@GetMapping(AppConstants.CONTROLLER_COMPONENT_BY_ID)
	@Operation(summary = "Get component by id ", description = "Return a component detail as per the id")
	@CommonApiResponses
	public Response componentById(@PathVariable Long id,@Parameter(hidden = true) @CurrentUser UserPrincipal user)
	{
		if(CommonFunctions.hasRole(user.getRole()))return componentsService.component(user,id);
		else { throw new NotAuthorisedException(AppConstants.NOT_AUTHORISED_STRING);}
	}

	
	@GetMapping(AppConstants.CONTROLLER_COMPONENT)
	@Operation(summary = "Get component records order by latest created", description = "Return a list of components")
	@CommonApiResponses
	public Response componentsList(@Parameter(hidden = true) @CurrentUser UserPrincipal user) 
	{
		if(CommonFunctions.hasRole(user.getRole())) return componentsService.component(user);
		else { throw new NotAuthorisedException(AppConstants.NOT_AUTHORISED_STRING);}
	}
	
	
	
	
	@PostMapping(AppConstants.CONTROLLER_MANAGE_COMPONENT_MASTER)	
	@Operation(summary = "Add/Update/Delete componentMaster record", description = "It will perform action(1=add,2=update,3=delete) as per action parameter value}")
	@CommonApiResponses
	public Response manageComponentMaster(@RequestBody Request request,@Parameter(hidden = true) @CurrentUser UserPrincipal user) 
	{	
		if(CommonFunctions.hasRole(user.getRole()))return componentMasterService.manageComponentsMaster(user,request);
		else { throw new NotAuthorisedException(AppConstants.NOT_AUTHORISED_STRING);}
	}	

	@GetMapping(AppConstants.CONTROLLER_COMPONENT_MASTER_BY_ID)
	@Operation(summary = "Get componentMaster by id ", description = "Return a componentMaster detail as per the id")
	@CommonApiResponses
	public Response componentMasterById(@PathVariable Long id,@Parameter(hidden = true) @CurrentUser UserPrincipal user)
	{
		if(CommonFunctions.hasRole(user.getRole()))return componentMasterService.componentsMaster(user,id);
		else { throw new NotAuthorisedException(AppConstants.NOT_AUTHORISED_STRING);}
	}	
	@GetMapping(AppConstants.CONTROLLER_COMPONENT_MASTER)
	@Operation(summary = "Get componentMaster records order by latest created", description = "Return a list of componentMaster")
	@CommonApiResponses
	public Response componentsMasterList(@Parameter(hidden = true) @CurrentUser UserPrincipal user) 
	{
		if(CommonFunctions.hasRole(user.getRole())) return componentMasterService.componentsMaster(user);
		else { throw new NotAuthorisedException(AppConstants.NOT_AUTHORISED_STRING);}
	}
	
	
	
	@PostMapping(AppConstants.CONTROLLER_MANAGE_STAGE)	
	@Operation(summary = "Add/Update/Delete stage record", description = "It will perform action(1=add,2=update,3=delete) as per action parameter value}")
	@CommonApiResponses
	public Response manageStage(@RequestBody Request request,@Parameter(hidden = true) @CurrentUser UserPrincipal user) 
	{	
		if(CommonFunctions.hasRole(user.getRole()))return stagesService.manageStages(user,request);
		else { throw new NotAuthorisedException(AppConstants.NOT_AUTHORISED_STRING);}
	}	

	@GetMapping(AppConstants.CONTROLLER_STAGE_BY_ID)
	@Operation(summary = "Get stage by id ", description = "Return a stage detail as per the id")
	@CommonApiResponses
	public Response stageById(@PathVariable Integer id,@Parameter(hidden = true) @CurrentUser UserPrincipal user)
	{
		if(CommonFunctions.hasRole(user.getRole()))return stagesService.getStages(user,id);
		else { throw new NotAuthorisedException(AppConstants.NOT_AUTHORISED_STRING);}
	}

	
	@GetMapping(AppConstants.CONTROLLER_STAGE)
	@Operation(summary = "Get stage records order by latest created", description = "Return a list of stages")
	@CommonApiResponses
	public Response stagesList(@Parameter(hidden = true) @CurrentUser UserPrincipal user) 
	{
		if(CommonFunctions.hasRole(user.getRole())) return stagesService.getStages(user);
		else { throw new NotAuthorisedException(AppConstants.NOT_AUTHORISED_STRING);}
	}
	
	
	
	@PostMapping(AppConstants.CONTROLLER_MANAGE_STAGE_FLOW)	
	@Operation(summary = "Add/Update/Delete stageFlow record", description = "It will perform action(1=add,2=update,3=delete) as per action parameter value}")
	@CommonApiResponses
	public Response manageStageFlow(@RequestBody Request request,@Parameter(hidden = true) @CurrentUser UserPrincipal user) 
	{	
		if(CommonFunctions.hasRole(user.getRole()))return stagesService.manageStagesFlow(user,request);
		else { throw new NotAuthorisedException(AppConstants.NOT_AUTHORISED_STRING);}
	}	

	@GetMapping(AppConstants.CONTROLLER_STAGE_FLOW_BY_ID)
	@Operation(summary = "Get stageFlow by id ", description = "Return a stage detail as per the id")
	@CommonApiResponses
	public Response stageFlowById(@PathVariable Integer id,@Parameter(hidden = true) @CurrentUser UserPrincipal user)
	{
		if(CommonFunctions.hasRole(user.getRole()))return stagesService.getStagesFlow(user,id);
		else { throw new NotAuthorisedException(AppConstants.NOT_AUTHORISED_STRING);}
	}

	
	@GetMapping(AppConstants.CONTROLLER_STAGE_FLOW)
	@Operation(summary = "Get stageFlow records order by latest created", description = "Return a list of stages")
	@CommonApiResponses
	public Response stagesFlowList(@Parameter(hidden = true) @CurrentUser UserPrincipal user) 
	{
		if(CommonFunctions.hasRole(user.getRole())) return stagesService.getStagesFlow(user);
		else { throw new NotAuthorisedException(AppConstants.NOT_AUTHORISED_STRING);}
	}
	
	
	
	@PostMapping(AppConstants.CONTROLLER_MANAGE_FACILITY)	
	@Operation(summary = "Add/Update/Delete facility record", description = "It will perform action(1=add,2=update,3=delete) as per action parameter value}")
	@CommonApiResponses
	public Response managefacilities(@RequestBody Request request,@Parameter(hidden = true) @CurrentUser UserPrincipal user) 
	{	
		if(CommonFunctions.hasRole(user.getRole()))return facilitiesService.manageFacilities(user,request);
		else { throw new NotAuthorisedException(AppConstants.NOT_AUTHORISED_STRING);}
	}	

	@GetMapping(AppConstants.CONTROLLER_FACILITY_BY_ID)
	@Operation(summary = "Get facility by id ", description = "Return a facility detail as per the id")
	@CommonApiResponses
	public Response facilitiesById(@PathVariable Integer id,@Parameter(hidden = true) @CurrentUser UserPrincipal user)
	{
		if(CommonFunctions.hasRole(user.getRole()))return facilitiesService.getFacilities(user,id);
		else { throw new NotAuthorisedException(AppConstants.NOT_AUTHORISED_STRING);}
	}

	
	@GetMapping(AppConstants.CONTROLLER_FACILITY)
	@Operation(summary = "Get facility records order by latest created", description = "Return a list of facilitys")
	@CommonApiResponses
	public Response facilitiesList(@Parameter(hidden = true) @CurrentUser UserPrincipal user) 
	{
		if(CommonFunctions.hasRole(user.getRole())) return facilitiesService.getFacilities(user);
		else { throw new NotAuthorisedException(AppConstants.NOT_AUTHORISED_STRING);}
	}
	
	
	
	@PostMapping(AppConstants.CONTROLLER_MANAGE_FACILITY_FLOW)	
	@Operation(summary = "Add/Update/Delete facilityFlow record", description = "It will perform action(1=add,2=update,3=delete) as per action parameter value}")
	@CommonApiResponses
	public Response manageFacilitiesFlow(@RequestBody Request request,@Parameter(hidden = true) @CurrentUser UserPrincipal user) 
	{	
		if(CommonFunctions.hasRole(user.getRole()))return facilitiesService.manageFacilitiesFlow(user,request);
		else { throw new NotAuthorisedException(AppConstants.NOT_AUTHORISED_STRING);}
	}	

	@GetMapping(AppConstants.CONTROLLER_FACILITY_FLOW_BY_ID)
	@Operation(summary = "Get facilityFlow by id ", description = "Return a facility detail as per the id")
	@CommonApiResponses
	public Response facilitiesFlowById(@PathVariable Integer id,@Parameter(hidden = true) @CurrentUser UserPrincipal user)
	{
		if(CommonFunctions.hasRole(user.getRole()))return facilitiesService.getFacilitiesFlow(user,id);
		else { throw new NotAuthorisedException(AppConstants.NOT_AUTHORISED_STRING);}
	}

	
	@GetMapping(AppConstants.CONTROLLER_FACILITY_FLOW)
	@Operation(summary = "Get facilityFlow records order by latest created", description = "Return a list of facilitys")
	@CommonApiResponses
	public Response facilitiesFlowList(@Parameter(hidden = true) @CurrentUser UserPrincipal user) 
	{
		if(CommonFunctions.hasRole(user.getRole())) return facilitiesService.getFacilitiesFlow(user);
		else { throw new NotAuthorisedException(AppConstants.NOT_AUTHORISED_STRING);}
	}
	
	
	
	
	
	
	
	
	
	
}
