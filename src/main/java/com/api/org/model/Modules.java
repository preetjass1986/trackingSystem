package com.api.org.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

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
	
	public Modules() {}
	
	public Modules(String name,Integer componentCount,Long createdBy) {
      this.name=name;
      this.componentCount=componentCount;
      this.createdBy=createdBy;
      this.createdOn=new Date();
		
	}
}
