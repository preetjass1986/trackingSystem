package com.api.org.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.api.org.model.audit.DateAudit;

import lombok.Data;

@Data
@Entity
//@JsonInclude(Include.NON_NULL)
@Table(name = "modules")
public class Modules extends DateAudit {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;
	
	private String name;
	
	private Integer componentCount;
	
	private Long createdBy;
}
