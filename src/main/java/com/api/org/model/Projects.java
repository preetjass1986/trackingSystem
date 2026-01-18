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

@Data
@Entity
//@JsonInclude(Include.NON_NULL)
@Table(name = "projects")
public class Projects extends DateAudit {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;
	
	private String name;
	
	private Integer status;
	
	private Long createdBy;
	
	public Projects() {}
	
	public Projects(String name,Long createdBy) {
		this.name=name;
		this.createdBy=createdBy;
		this.createdOn=new Date();
	}


}
