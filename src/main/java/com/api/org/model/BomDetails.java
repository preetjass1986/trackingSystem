package com.api.org.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bom_details")
public class BomDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long projectId;

      private String motherPartNo;

   
    private String motherPartDesc;


    private String childPartNo;

   
    private String childPartDesc;

 
    private Integer quantity;

 
    private String unit;

  
    private String type;


    private String gaIssueLevel;


    private String plIssueLevel;


    private String reserve1;

  
    private String reserve2;

 
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

  

    public BomDetails() {
    }

    // ===== Getters & Setters =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long id) {
        this.projectId = id;
    }

    public String getMotherPartNo() {
        return motherPartNo;
    }

    public void setMotherPartNo(String motherPartNo) {
        this.motherPartNo = motherPartNo;
    }

    public String getMotherPartDesc() {
        return motherPartDesc;
    }

    public void setMotherPartDesc(String motherPartDesc) {
        this.motherPartDesc = motherPartDesc;
    }

    public String getChildPartNo() {
        return childPartNo;
    }

    public void setChildPartNo(String childPartNo) {
        this.childPartNo = childPartNo;
    }

    public String getChildPartDesc() {
        return childPartDesc;
    }

    public void setChildPartDesc(String childPartDesc) {
        this.childPartDesc = childPartDesc;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGaIssueLevel() {
        return gaIssueLevel;
    }

    public void setGaIssueLevel(String gaIssueLevel) {
        this.gaIssueLevel = gaIssueLevel;
    }

    public String getPlIssueLevel() {
        return plIssueLevel;
    }

    public void setPlIssueLevel(String plIssueLevel) {
        this.plIssueLevel = plIssueLevel;
    }

    public String getReserve1() {
        return reserve1;
    }

    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1;
    }

    public String getReserve2() {
        return reserve2;
    }

    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
