package com.api.org.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.api.org.model.audit.DateAudit;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=false)
@Entity
//@JsonInclude(Include.NON_NULL)
@Table(name = "users_log")
public class UsersLog   extends DateAudit{
		
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	private String ani;	
	private String email;		
	private String userName;	
	@JsonIgnore
	private String password;
	
	private Integer role;
	private Integer status;
	private Integer serviceId;
	private String circle;
	private String gender;
	private Integer age;
	
	
	public UsersLog() {}
	
	public UsersLog(Users user,String unsubMode) {
		this.ani=""+user.getAni();
		this.email=user.getEmail();
		this.userName=user.getUserName();
		this.password=user.getPassword();
		this.status=user.getStatus();
		this.role=user.getRole();
		this.createdOn=user.getCreatedOn();
		this.updatedOn=new Date();	
	}

}
