package com.api.org.view;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.api.org.model.Users;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class UserInfo {

	private Long id;

	private String email;		
	private String userName;		
	private Integer amount;
	private Long sou;
	private Integer role;
	private Integer serviceId;
	private Integer planId;
	private String clickId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "IST")
	private Date fromDate;	
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "IST")
	private Date toDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "IST")
	private Date createdOn;	
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "IST")
	private Date updatedOn;
	
	
	
	public UserInfo() {
		
	}
	
    public UserInfo(Users user) {
    	this.id=user.getId();
    	this.userName=user.getUserName();
    	this.updatedOn=user.getUpdatedOn();
	}
}
