package com.api.org.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.api.org.model.audit.DateAudit;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
//@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@Table(name = "stats_master")
public class StatusMaster extends DateAudit {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;
	
	private String detail;

}
