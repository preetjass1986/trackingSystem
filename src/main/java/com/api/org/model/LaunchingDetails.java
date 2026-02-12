package com.api.org.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "launching_details")
public class LaunchingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "launching_no", length = 50)
    private String launchingNo;

    @Column(name = "facility", length = 20)
    private String facility;

    @Column(name = "project_name", length = 100)
    private String projectName;

    @Column(name = "project_id", length = 20)
    private String projectId;

    @Column(name = "material_no", length = 20)
    private String materialNo;

    @Column(name = "description", length = 200)
    private String description;

    @Column(name = "type_of_material", length = 20)
    private String typeOfMaterial;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "sr_no_from")
    private Long srNoFrom;

    @Column(name = "sr_no_to")
    private Long srNoTo;

    @Column(name = "issue_level_ga", length = 20)
    private String issueLevelGa;

    @Column(name = "issue_level_pa", length = 20)
    private String issueLevelPa;

    @Column(name = "completion_date")
    private LocalDate completionDate;

    @Column(name = "estimated_hours")
    private Double estimatedHours;

    @Column(name = "green_channel")
    private Boolean greenChannel;

    @Column(name = "direct_saleable")
    private Boolean directSaleable;

    @Column(name = "routing", length = 20)
    private String routing;

    @Column(name = "direct_iust", length = 20)
    private String directIust;

    @Column(name = "edtimated_cogs", length = 20)
    private String edtimatedCogs;

    @Column(name = "reserve_1", length = 50)
    private String reserve1;

    @Column(name = "reserve_2", length = 50)
    private String reserve2;

    @Column(name = "reserve_3", length = 50)
    private String reserve3;

    // ===== Constructors =====

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
