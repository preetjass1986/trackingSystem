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

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="users_session",
    indexes = {
        @Index(name = "idx_users_session_userid", columnList = "userid")
    })
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UsersSession extends DateAudit{
	
	@EqualsAndHashCode.Include
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)	
	 private Long id; 
	 
	 @CreatedDate
	 @Temporal(TemporalType.TIMESTAMP)
	 @Column(name = "created_on", updatable = false)
	 @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "IST")
	 private Date createdOn;

	 
	 @Column(name = "userid", nullable = false)
	 private Long userid;

	 @Column(name = "token", length = 500, nullable = false)
	 private String token;

	 @Column(name = "header", length = 50)
	 private String header;


}
