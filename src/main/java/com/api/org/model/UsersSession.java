package com.api.org.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

import com.api.org.model.audit.DateAudit;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="users_session")
public class UsersSession extends DateAudit{
	
	 @EqualsAndHashCode.Include
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)	
	 private Long id; 
	 	 
	 private Long userid;
	 
	 private String token;

	 private String header;


}
