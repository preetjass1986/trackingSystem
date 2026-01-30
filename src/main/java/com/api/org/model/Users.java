package com.api.org.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(
    name = "users",
    indexes = {
        @Index(name = "idx_user_role", columnList = "role")
    }
)
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Users extends DateAudit {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String name;

    @Column(length = 10, unique = true)
    private String ani;

    @Column(name = "user_name", length = 20)
    private String userName;

    @JsonIgnore
    @Column(length = 200)
    private String password;

    @Column(length = 40)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role", referencedColumnName = "role_id",
    insertable = false,
    updatable = false)
    private Roles roleEntity;
    @Column(name = "role")
    private Integer role;


    @Column(name = "status", columnDefinition = "int(2) default 1")
    private Integer status=1;

    @Column(name = "sapId", length = 50)
    private String sapId;

    @Lob
    @Column(name = "thumbIsoTemplate", columnDefinition = "LONGTEXT")
    private String thumbIsoTemplate;
}
