package com.api.org.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.org.constants.AppConstants;
import com.api.org.exception.BadRequestException;
import com.api.org.exception.NotAuthorisedException;
import com.api.org.security.CurrentUser;
import com.api.org.security.UserPrincipal;
import com.api.org.service.ComponentsMasterService;
import com.api.org.service.ComponentsService;
import com.api.org.service.ModulesService;
import com.api.org.service.ProjectsService;
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
	
	
	
	
	@PostMapping(AppConstants.CONTROLLER_MANAGE_PROJECT)	
	@Operation(summary = "Add/Update/Delete project record", description = "It will perform action(1=add,2=update,3=delete) as per action parameter value}")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "{\"message\": \"Success\"}"), 
	        @ApiResponse(responseCode = "400", description = "{\"message\": \"Required parameter missing\"}")
	    })
	public Response manageProject(@RequestBody Request request,@Parameter(hidden = true) @CurrentUser UserPrincipal user) 
	{	
		if(CommonFunctions.hasRole(user.getRole()))
		{
			return projectsService.manageProject(user,request);
		}
		else { throw new NotAuthorisedException(AppConstants.NOT_AUTHORISED_STRING);}
	}

	@GetMapping(AppConstants.CONTROLLER_PROJECT_BY_ID)
	@Operation(summary = "Get project record by id ", description = "Return a project detail as per the id")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "{\"message\": \"Success\",\"data\":{\"id\": 1,\"createdBy\": 15,\"surveyName\": \"School Survey\",\"content\": \"{\"title\":\"School Survey\",\"completedHtml\":\"<h3>Thank you for your feedback</h3>\"}\",\"token\": \"d092caa8-50e8-4f69-bde3-1e6886918fec1714203413620\", \"createdOn\": \"2024-04-27 13:06:53\"}}"), 
	        @ApiResponse(responseCode = "400", description = "{\"message\": \"Required parameter missing\"}"),
	        @ApiResponse(responseCode = "404", description = "{\"message\": \"No record available\"}")
	    })
	public Response projectById(@PathVariable Long id,@Parameter(hidden = true) @CurrentUser UserPrincipal user)
	{
		if(CommonFunctions.hasRole(user.getRole()))return projectsService.project(user,id);
		else { throw new NotAuthorisedException(AppConstants.NOT_AUTHORISED_STRING);}
	}

	
	@GetMapping(AppConstants.CONTROLLER_PROJECT)
	@Operation(summary = "Get project records order by latest created", description = "Return a list of projects")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "{\"message\": \"Success\",\"data\":[{\"id\": 1,\"createdBy\": 15,\"surveyName\": \"School Survey\",\"content\": \"{\"title\":\"School Survey\",\"completedHtml\":\"<h3>Thank you for your feedback</h3>\"}\",\"token\": \"d092caa8-50e8-4f69-bde3-1e6886918fec1714203413620\", \"createdOn\": \"2024-04-27 13:06:53\"}]}"), 
	        @ApiResponse(responseCode = "400", description = "{\"message\": \"Required parameter missing\"}"),
	        @ApiResponse(responseCode = "404", description = "{\"message\": \"No record available\"}")
	    })
	public Response projectList(@Parameter(hidden = true) @CurrentUser UserPrincipal user) 
	{
		if(CommonFunctions.hasRole(user.getRole()))return projectsService.project(user);
		else { throw new NotAuthorisedException(AppConstants.NOT_AUTHORISED_STRING);}
	}
	
	
	@PostMapping(AppConstants.CONTROLLER_MANAGE_MODULE)	
	@Operation(summary = "Add/Update/Delete module record", description = "It will perform action(1=add,2=update,3=delete) as per action parameter value}")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "{\"message\": \"Success\"}"), 
	        @ApiResponse(responseCode = "400", description = "{\"message\": \"Required parameter missing\"}")
	    })
	public Response manageModule(@RequestBody Request request,@Parameter(hidden = true) @CurrentUser UserPrincipal user) 
	{	
		if(CommonFunctions.hasRole(user.getRole()))return modulesService.manageModule(user,request);
		else { throw new NotAuthorisedException(AppConstants.NOT_AUTHORISED_STRING);}
	}	

	@GetMapping(AppConstants.CONTROLLER_MODULE_BY_ID)
	@Operation(summary = "Get module by id ", description = "Return a module detail as per the id")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "{\"message\": \"Success\",\"data\":{\"id\": 1,\"createdBy\": 15,\"surveyName\": \"School Survey\",\"content\": \"{\"title\":\"School Survey\",\"completedHtml\":\"<h3>Thank you for your feedback</h3>\"}\",\"token\": \"d092caa8-50e8-4f69-bde3-1e6886918fec1714203413620\", \"createdOn\": \"2024-04-27 13:06:53\"}}"), 
	        @ApiResponse(responseCode = "400", description = "{\"message\": \"Required parameter missing\"}"),
	        @ApiResponse(responseCode = "404", description = "{\"message\": \"No record available\"}")
	    })
	public Response moduleById(@PathVariable Long id,@Parameter(hidden = true) @CurrentUser UserPrincipal user)
	{
		if(CommonFunctions.hasRole(user.getRole()))return modulesService.module(user,id);
		else { throw new NotAuthorisedException(AppConstants.NOT_AUTHORISED_STRING);}
	}

	
	@GetMapping(AppConstants.CONTROLLER_MODULE)
	@Operation(summary = "Get module records order by latest created", description = "Return a list of projects")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "{\"message\": \"Success\",\"data\":[{\"id\": 1,\"createdBy\": 15,\"surveyName\": \"School Survey\",\"content\": \"{\"title\":\"School Survey\",\"completedHtml\":\"<h3>Thank you for your feedback</h3>\"}\",\"token\": \"d092caa8-50e8-4f69-bde3-1e6886918fec1714203413620\", \"createdOn\": \"2024-04-27 13:06:53\"}]}"), 
	        @ApiResponse(responseCode = "400", description = "{\"message\": \"Required parameter missing\"}"),
	        @ApiResponse(responseCode = "404", description = "{\"message\": \"No record available\"}")
	    })
	public Response modulesList(@Parameter(hidden = true) @CurrentUser UserPrincipal user) 
	{
		if(CommonFunctions.hasRole(user.getRole())) return modulesService.module(user);
		else { throw new NotAuthorisedException(AppConstants.NOT_AUTHORISED_STRING);}
	}
	
	
	
	
	@PostMapping(AppConstants.CONTROLLER_MANAGE_COMPONENT)	
	@Operation(summary = "Add/Update/Delete component record", description = "It will perform action(1=add,2=update,3=delete) as per action parameter value}")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "{\"message\": \"Success\"}"), 
	        @ApiResponse(responseCode = "400", description = "{\"message\": \"Required parameter missing\"}")
	    })
	public Response manageComponent(@RequestBody Request request,@Parameter(hidden = true) @CurrentUser UserPrincipal user) 
	{	
		if(CommonFunctions.hasRole(user.getRole()))return componentsService.manageComponent(user,request);
		else { throw new NotAuthorisedException(AppConstants.NOT_AUTHORISED_STRING);}
	}	

	@GetMapping(AppConstants.CONTROLLER_COMPONENT_BY_ID)
	@Operation(summary = "Get component by id ", description = "Return a component detail as per the id")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "{\"message\": \"Success\",\"data\":{\"id\": 1,\"createdBy\": 15,\"surveyName\": \"School Survey\",\"content\": \"{\"title\":\"School Survey\",\"completedHtml\":\"<h3>Thank you for your feedback</h3>\"}\",\"token\": \"d092caa8-50e8-4f69-bde3-1e6886918fec1714203413620\", \"createdOn\": \"2024-04-27 13:06:53\"}}"), 
	        @ApiResponse(responseCode = "400", description = "{\"message\": \"Required parameter missing\"}"),
	        @ApiResponse(responseCode = "404", description = "{\"message\": \"No record available\"}")
	    })
	public Response componentById(@PathVariable Long id,@Parameter(hidden = true) @CurrentUser UserPrincipal user)
	{
		if(CommonFunctions.hasRole(user.getRole()))return componentsService.component(user,id);
		else { throw new NotAuthorisedException(AppConstants.NOT_AUTHORISED_STRING);}
	}

	
	@GetMapping(AppConstants.CONTROLLER_COMPONENT)
	@Operation(summary = "Get component records order by latest created", description = "Return a list of components")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "{\"message\": \"Success\",\"data\":[{\"id\": 1,\"createdBy\": 15,\"surveyName\": \"School Survey\",\"content\": \"{\"title\":\"School Survey\",\"completedHtml\":\"<h3>Thank you for your feedback</h3>\"}\",\"token\": \"d092caa8-50e8-4f69-bde3-1e6886918fec1714203413620\", \"createdOn\": \"2024-04-27 13:06:53\"}]}"), 
	        @ApiResponse(responseCode = "400", description = "{\"message\": \"Required parameter missing\"}"),
	        @ApiResponse(responseCode = "404", description = "{\"message\": \"No record available\"}")
	    })
	public Response componentsList(@Parameter(hidden = true) @CurrentUser UserPrincipal user) 
	{
		if(CommonFunctions.hasRole(user.getRole())) return componentsService.component(user);
		else { throw new NotAuthorisedException(AppConstants.NOT_AUTHORISED_STRING);}
	}
	
	
	
	
	@PostMapping(AppConstants.CONTROLLER_MANAGE_COMPONENT_MASTER)	
	@Operation(summary = "Add/Update/Delete componentMaster record", description = "It will perform action(1=add,2=update,3=delete) as per action parameter value}")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "{\"message\": \"Success\"}"), 
	        @ApiResponse(responseCode = "400", description = "{\"message\": \"Required parameter missing\"}")
	    })
	public Response manageComponentMaster(@RequestBody Request request,@Parameter(hidden = true) @CurrentUser UserPrincipal user) 
	{	
		if(CommonFunctions.hasRole(user.getRole()))return componentMasterService.manageComponentsMaster(user,request);
		else { throw new NotAuthorisedException(AppConstants.NOT_AUTHORISED_STRING);}
	}	

	@GetMapping(AppConstants.CONTROLLER_COMPONENT_MASTER_BY_ID)
	@Operation(summary = "Get componentMaster by id ", description = "Return a componentMaster detail as per the id")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "{\"message\": \"Success\",\"data\":{\"id\": 1,\"createdBy\": 15,\"surveyName\": \"School Survey\",\"content\": \"{\"title\":\"School Survey\",\"completedHtml\":\"<h3>Thank you for your feedback</h3>\"}\",\"token\": \"d092caa8-50e8-4f69-bde3-1e6886918fec1714203413620\", \"createdOn\": \"2024-04-27 13:06:53\"}}"), 
	        @ApiResponse(responseCode = "400", description = "{\"message\": \"Required parameter missing\"}"),
	        @ApiResponse(responseCode = "404", description = "{\"message\": \"No record available\"}")
	    })
	public Response componentMasterById(@PathVariable Long id,@Parameter(hidden = true) @CurrentUser UserPrincipal user)
	{
		if(CommonFunctions.hasRole(user.getRole()))return componentMasterService.componentsMaster(user,id);
		else { throw new NotAuthorisedException(AppConstants.NOT_AUTHORISED_STRING);}
	}

	
	@GetMapping(AppConstants.CONTROLLER_COMPONENT_MASTER)
	@Operation(summary = "Get componentMaster records order by latest created", description = "Return a list of componentMaster")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "{\"message\": \"Success\",\"data\":[{\"id\": 1,\"createdBy\": 15,\"surveyName\": \"School Survey\",\"content\": \"{\"title\":\"School Survey\",\"completedHtml\":\"<h3>Thank you for your feedback</h3>\"}\",\"token\": \"d092caa8-50e8-4f69-bde3-1e6886918fec1714203413620\", \"createdOn\": \"2024-04-27 13:06:53\"}]}"), 
	        @ApiResponse(responseCode = "400", description = "{\"message\": \"Required parameter missing\"}"),
	        @ApiResponse(responseCode = "404", description = "{\"message\": \"No record available\"}")
	    })
	public Response componentsMasterList(@Parameter(hidden = true) @CurrentUser UserPrincipal user) 
	{
		if(CommonFunctions.hasRole(user.getRole())) return componentMasterService.componentsMaster(user);
		else { throw new NotAuthorisedException(AppConstants.NOT_AUTHORISED_STRING);}
	}
	
	
	
	
	
	
}
