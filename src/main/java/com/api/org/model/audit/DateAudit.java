package com.api.org.model.audit;

import lombok.Data;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.api.org.constants.AppConstants;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class DateAudit  {

	@JsonFormat(pattern =AppConstants.DATE_FORMAT,timezone=AppConstants.TIMEZONE)
    @CreatedDate
    protected Date createdOn;

	@JsonFormat(pattern =AppConstants.DATE_FORMAT,timezone=AppConstants.TIMEZONE)
    @LastModifiedDate
    protected Date updatedOn;

	
}
