package com.realdolmen.fleet.domain;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Date;

@Entity
public class UserCarHistory implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    private Boolean replacement = false;

    @Min(-1)
    @Max(1)
    private Integer choosenLevel = 0;

    private Integer functionalLevelCopy;

    @Min(0)
    @Max(500_000)
    private Integer estimatedMileage;

    @ManyToOne
    private User user;

    @ManyToOne
    private CompanyCar companyCar;

    /* Used by JPA */
    public UserCarHistory() {
        this.user = new User();
        this.companyCar = new CompanyCar();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getReplacement() {
        return replacement;
    }

    public void setReplacement(Boolean replacement) {
        this.replacement = replacement;
    }

    public Integer getChoosenLevel() {
        return choosenLevel;
    }

    public void setChoosenLevel(Integer choosenLevel) {
        this.choosenLevel = choosenLevel;
    }

    public Integer getFunctionalLevelCopy() {
        return functionalLevelCopy;
    }

    public void setFunctionalLevelCopy(Integer functionalLevelCopy) {
        this.functionalLevelCopy = functionalLevelCopy;
    }

    public Integer getEstimatedMileage() {
        return estimatedMileage;
    }

    public void setEstimatedMileage(Integer estimatedMileage) {
        this.estimatedMileage = estimatedMileage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CompanyCar getCompanyCar() {
        return companyCar;
    }

    public void setCompanyCar(CompanyCar companyCar) {
        this.companyCar = companyCar;
    }
}
