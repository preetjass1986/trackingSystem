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
@Table(name = "survey_results")
public class SurveyResults {
	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	private Long schemaId;
	
    private Long createdBy;    
    
    private String  content;
  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "IST")
	 private Date createdOn; 
        
    public SurveyResults() {

    }
 
    

}
