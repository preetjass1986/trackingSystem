
package com.api.org.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.api.org.constants.AppConstants;
import com.api.org.model.audit.DateAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
//@JsonInclude(Include.NON_NULL)
@Table(name = "components")
public class Components extends DateAudit {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;
	
	private Long projectId;
	
	private Long moduleId;
	
	private Integer componentsCount;
	
	private String name;
	
	private Integer status;
	
	public Components() {
		
	}
	
	public Components(Long projectId,Long moduleId,Integer componentsCount,String name) {
		this.projectId=projectId;
		this.moduleId=moduleId;
		this.componentsCount=componentsCount;
		this.name=name;
		this.createdOn=new Date();
		this.status=AppConstants.ONE;		
	}
	



}

