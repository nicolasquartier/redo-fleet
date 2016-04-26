package com.realdolmen.fleet.domain;

import com.realdolmen.fleet.domain.enums.Brand;
import com.realdolmen.fleet.domain.enums.CarType;
import com.realdolmen.fleet.domain.enums.FuelType;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Car implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    @Enumerated(EnumType.STRING)
    private Brand brand;

    @Column(length = 100)
    @Size(max = 100)
    private String model;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date productionDate;

    @Min(value = 0)
    private Integer fiscalHorsePower;

    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    @Column(length = 350)
    @Size(max = 350)
    private String pack;

    @Enumerated(EnumType.STRING)
    private CarType type;

    @OneToMany(mappedBy = "car")
    private List<Option> options = new ArrayList<>();

    private Boolean hybrid;

    @Min(value = 0)
    private Integer emission;

    private Boolean active;

    @ManyToOne
    private FunctionalLevel category;

    private String thumbnail;

    /* Used by JPA */
    public Car() {
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public Integer getFiscalHorsePower() {
        return fiscalHorsePower;
    }

    public void setFiscalHorsePower(Integer fiscalHorsePower) {
        this.fiscalHorsePower = fiscalHorsePower;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        this.type = type;
    }

    public Boolean isHybrid() {
        return hybrid;
    }

    public void setHybrid(Boolean hybrid) {
        this.hybrid = hybrid;
    }

    public Integer getEmission() {
        return emission;
    }

    public void setEmission(Integer emission) {
        this.emission = emission;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public FunctionalLevel getCategory() {
        return category;
    }

    public void setCategory(FunctionalLevel category) {
        this.category = category;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
