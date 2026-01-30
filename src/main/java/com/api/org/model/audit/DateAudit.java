package com.api.org.model.audit;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.api.org.constants.AppConstants;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class DateAudit {

    @JsonFormat(pattern = AppConstants.DATE_FORMAT, timezone = AppConstants.TIMEZONE)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdOn;

    @JsonFormat(pattern = AppConstants.DATE_FORMAT, timezone = AppConstants.TIMEZONE)
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date updatedOn;
}

