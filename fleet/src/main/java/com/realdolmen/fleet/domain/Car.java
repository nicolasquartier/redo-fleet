package com.realdolmen.fleet.domain;

import com.realdolmen.fleet.domain.enums.Brand;
import com.realdolmen.fleet.domain.enums.CarType;
import com.realdolmen.fleet.domain.enums.FuelType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
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

    private int fiscalHorsePower;

    @Enumerated(EnumType.STRING)
    private FuelType fuelType;
    @Column(length = 350)
    @Size(max = 350)
    private String pack;

    @Enumerated(EnumType.STRING)
    private CarType type;

    @OneToMany(mappedBy = "car")
    private List<Option> options;

    private boolean hybrid;

    private int emission;

    private boolean active;

    @NotNull
    @ManyToOne
    private FunctionalLevel category;

    public Car() {
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

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public void setFiscalHorsePower(int fiscalHorsePower) {
        this.fiscalHorsePower = fiscalHorsePower;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public void setType(CarType type) {
        this.type = type;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public void setHybrid(boolean hybrid) {
        this.hybrid = hybrid;
    }

    public void setEmission(int emission) {
        this.emission = emission;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setCategory(FunctionalLevel category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public Brand getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public int getFiscalHorsePower() {
        return fiscalHorsePower;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public String getPack() {
        return pack;
    }

    public CarType getType() {
        return type;
    }

    public boolean isHybrid() {
        return hybrid;
    }

    public int getEmission() {
        return emission;
    }

    public boolean isActive() {
        return active;
    }

    public FunctionalLevel getCategory() {
        return category;
    }

}
