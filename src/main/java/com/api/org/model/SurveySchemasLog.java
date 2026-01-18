package com.api.org.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@Entity
@JsonInclude(Include.NON_NULL)
@Table(name = "survey_schemas_log")
public class SurveySchemasLog {
	
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
    
    public SurveySchemasLog() {

    }
    
    public SurveySchemasLog(SurveySchemas surveySchemas,Long updatedBy) {
    	this.surveyName=surveySchemas.getSurveyName();
    	this.token=surveySchemas.getToken();
    	this.content=surveySchemas.getContent();
    	this.createdBy=surveySchemas.getCreatedBy();
    	this.createdOn=surveySchemas.getCreatedOn();
    	this.updatedOn=new Date();
    	this.updatedBy=updatedBy;

    }
    

}
