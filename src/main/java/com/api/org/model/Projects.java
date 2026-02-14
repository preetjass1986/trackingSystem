package com.api.org.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.api.org.model.audit.DateAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
//@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@Table(name = "projects")
public class Projects extends DateAudit {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;
	
	private String name;
	
	private Integer status;
	
	private Long createdBy;
	
	private Date createdOn;
	
	private Date updatedOn;
	
	private String productCode;
	
	public Projects(String name,Long createdBy,String productCode,Long userId) {
		this.name=name;
		this.createdBy=createdBy;
		this.createdOn=new Date();
		this.productCode=productCode;
		
	}


}
