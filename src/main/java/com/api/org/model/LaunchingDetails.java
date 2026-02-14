package com.api.org.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "launching_details")
public class LaunchingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

 
    private String launchingNo;


    private String facility;

 
    private String projectName;


    private String projectId;


    private String materialNo;


    private String description;

  
    private String typeOfMaterial;


    private Integer quantity;

 
    private Long srNoFrom;


    private Long srNoTo;


    private String issueLevelGa;


    private String issueLevelPa;

 
    private LocalDate completionDate;


    private Double estimatedHours;


    private Boolean greenChannel;


    private Boolean directSaleable;


    private String routing;


    private String directIust;


    private String edtimatedCogs;

  
    private String reserve1;


    private String reserve2;


    private String reserve3;

 

    public LaunchingDetails() {
    }

    // ===== Getters & Setters =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLaunchingNo() {
        return launchingNo;
    }

    public void setLaunchingNo(String launchingNo) {
        this.launchingNo = launchingNo;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getMaterialNo() {
        return materialNo;
    }

    public void setMaterialNo(String materialNo) {
        this.materialNo = materialNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeOfMaterial() {
        return typeOfMaterial;
    }

    public void setTypeOfMaterial(String typeOfMaterial) {
        this.typeOfMaterial = typeOfMaterial;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getSrNoFrom() {
        return srNoFrom;
    }

    public void setSrNoFrom(Long srNoFrom) {
        this.srNoFrom = srNoFrom;
    }

    public Long getSrNoTo() {
        return srNoTo;
    }

    public void setSrNoTo(Long srNoTo) {
        this.srNoTo = srNoTo;
    }

    public String getIssueLevelGa() {
        return issueLevelGa;
    }

    public void setIssueLevelGa(String issueLevelGa) {
        this.issueLevelGa = issueLevelGa;
    }

    public String getIssueLevelPa() {
        return issueLevelPa;
    }

    public void setIssueLevelPa(String issueLevelPa) {
        this.issueLevelPa = issueLevelPa;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    public Double getEstimatedHours() {
        return estimatedHours;
    }

    public void setEstimatedHours(Double estimatedHours) {
        this.estimatedHours = estimatedHours;
    }

    public Boolean getGreenChannel() {
        return greenChannel;
    }

    public void setGreenChannel(Boolean greenChannel) {
        this.greenChannel = greenChannel;
    }

    public Boolean getDirectSaleable() {
        return directSaleable;
    }

    public void setDirectSaleable(Boolean directSaleable) {
        this.directSaleable = directSaleable;
    }

    public String getRouting() {
        return routing;
    }

    public void setRouting(String routing) {
        this.routing = routing;
    }

    public String getDirectIust() {
        return directIust;
    }

    public void setDirectIust(String directIust) {
        this.directIust = directIust;
    }

    public String getEdtimatedCogs() {
        return edtimatedCogs;
    }

    public void setEdtimatedCogs(String edtimatedCogs) {
        this.edtimatedCogs = edtimatedCogs;
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

    public String getReserve3() {
        return reserve3;
    }

    public void setReserve3(String reserve3) {
        this.reserve3 = reserve3;
    }
}
