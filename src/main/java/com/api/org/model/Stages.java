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
@Table(name = "stages")
public class Stages extends DateAudit {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	
	private String name;
	
	private String code;
	
	private Integer status ;
		
	private Integer facilityId;	
	
	private Integer sequenceNo ;
	
	private Long createdBy ;
	
	public Stages(String name,String code,Integer facilityId,Integer sequenceNo,Long createdBy) {
		this.status=AppConstants.ONE;				
		this.name=name;
		this.code=code;
		this.facilityId=facilityId;
		this.sequenceNo=sequenceNo;
		this.createdBy=createdBy;
		this.createdOn=new Date();
	}
	
}
