package com.realdolmen.fleet.domain;

import com.realdolmen.fleet.domain.enums.OptionType;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "caroption")
public class Option implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Car car;

    @ManyToMany(mappedBy = "options")
    private List<CompanyCar> companyCarList = new ArrayList<>();

    @Column(length = 300)
    @Size(max = 300)
    private String description;

    @Enumerated(EnumType.STRING)
    private OptionType type;

    @Version
    private Long version;

    /* Used by JPA */
    public Option() {
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OptionType getType() {
        return type;
    }

    public void setType(OptionType type) {
        this.type = type;
    }

    public List<CompanyCar> getCompanyCarList() {
        return Collections.unmodifiableList(companyCarList);
    }

    public void setCompanyCarList(List<CompanyCar> companyCarList) {
        this.companyCarList = companyCarList;
    }

    public void addCompanyCar(CompanyCar companyCar) {
        this.companyCarList.add(companyCar);
    }

    public void removeCompanyCar(CompanyCar companyCar) {
        this.companyCarList.remove(companyCar);
    }
}
