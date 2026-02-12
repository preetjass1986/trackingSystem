package com.api.org.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bom_details")
public class BomDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mother_part_no", length = 50)
    private String motherPartNo;

    @Column(name = "mother_part_desc", length = 150)
    private String motherPartDesc;

    @Column(name = "child_part_no", length = 50)
    private String childPartNo;

    @Column(name = "child_part_desc", length = 150)
    private String childPartDesc;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "unit", length = 20)
    private String unit;

    @Column(name = "type", length = 50)
    private String type;

    @Column(name = "ga_issue_level", length = 20)
    private String gaIssueLevel;

    @Column(name = "pl_issue_level", length = 20)
    private String plIssueLevel;

    @Column(name = "reserve_1", length = 20)
    private String reserve1;

    @Column(name = "reserve_2", length = 20)
    private String reserve2;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // ===== Constructors =====

    public BomDetails() {
    }

    // ===== Getters & Setters =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
