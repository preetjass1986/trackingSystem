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
@Table(name = "stages_flow")
public class StagesFlow extends DateAudit {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	
	private Long projectId;	
	
	private Integer facilityId;	
		
	private Integer stageId;	
	
	private Integer sequenceNo ;	

	private Integer status ;
	
	private Long createdBy ;
	
	public StagesFlow(Long projectId,Integer facilityId,Integer stageId,Integer sequenceNo,Long createdBy) {
		this.status=AppConstants.ONE;
		this.projectId=projectId;
		this.facilityId=facilityId;
		this.stageId=stageId;
		this.sequenceNo=sequenceNo;
		this.createdBy=createdBy;
		this.createdOn=new Date();
	}
	
}
