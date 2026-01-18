package com.api.org.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.api.org.view.Request;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@Entity
@JsonInclude(Include.NON_NULL)
@Table(name = "survey_schemas")
public class SurveySchemas {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long createdBy;
    
    private Long updatedBy;
    
    private String  surveyName;
    
    private String  content;
    
    private String  token;
  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "IST")
	 private Date createdOn; 
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "IST")
	 private Date updatedOn; 
    
    public SurveySchemas() {

    }
    
    public SurveySchemas(Request surveyRequest,Long createdBy) {
    	this.content=surveyRequest.getContent();
    	this.createdBy=createdBy;    	
    	this.token=surveyRequest.getToken();
    	this.surveyName=surveyRequest.getName();
    	this.createdOn=new Date();	

    }
   
   

}
