package com.api.org.model;

import static javax.persistence.GenerationType.IDENTITY;

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
@Table(name = "components_detail")
public class ComponentsDetail extends DateAudit {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;
	
	private Long componentId;
	
	private String code;
	
	private Integer status;
	
	private Long createdBy;
	
	private Long updatedBy;


}
