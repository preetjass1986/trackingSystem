package com.api.org.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
	
	@Data
	@Entity
	@JsonInclude(Include.NON_NULL)
	@Table(name = "users_role")
	public class Roles {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;

	    private Integer roleId;
	    
	    @Column(length = 60)
	    private String role;
	    
	    private String menuJson;

	    public Roles() {

	    }
	   
	}

