package com.api.org.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.api.org.model.audit.DateAudit;
import com.api.org.security.UserPrincipal;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper=false)
@Entity
//@JsonInclude(Include.NON_NULL)
@Table(name = "users")
public class Users extends DateAudit {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	private String name;	
	private String ani;		
	private String userName;	
	@JsonIgnore
	private String password;
	
	private String email;
	private Integer role;
	private Integer status;
	private Integer serviceId;
	private String circle;
	private String gender;
	private Integer age;
	

	public Users() {	
	}
	

	
}
