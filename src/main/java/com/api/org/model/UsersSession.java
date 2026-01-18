package com.api.org.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
@Table(name="users_session")
public class UsersSession {
	
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)	
	 private Long id; 
	 
	 @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "IST")
	 private Date createdOn; 
	 
	 private Long userid ;
	 private String token;
	 private String header;

}
