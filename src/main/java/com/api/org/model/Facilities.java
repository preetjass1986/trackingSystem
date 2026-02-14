package com.api.org.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.api.org.constants.AppConstants;
import com.api.org.model.audit.DateAudit;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
//@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@Table(name = "facilities")
public class Facilities  extends DateAudit {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	
	private String name;
	
	private String code;
	
	private Integer status;
	
	private Long createdBy;
	
	public Facilities(String name,String code,Long createdBy) {
		this.status=AppConstants.ONE;	
		this.name=name;
		this.code=code;
		this.createdBy=createdBy;
		this.createdOn=new Date();
	}

}
