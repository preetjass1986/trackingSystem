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
@Table(name = "facilities_flow")
public class FacilitiesFlow extends DateAudit {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	
	private Integer facilityId;
	
	private Long projectId;
	
	private Integer sequenceNo ;
	
	private Integer status ;
	
	private Long createdBy ;
	
	public FacilitiesFlow(Long projectId,Integer facilityId,Integer sequenceNo,Long createdBy) {
		this.status=AppConstants.ONE;	
		this.projectId=projectId;
		this.facilityId=facilityId;
		this.sequenceNo=sequenceNo;
		this.createdBy=createdBy;
		this.createdOn=new Date();
	}

	

}
