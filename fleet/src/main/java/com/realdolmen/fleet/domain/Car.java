package com.realdolmen.fleet.domain;

import com.realdolmen.fleet.domain.enums.Brand;
import com.realdolmen.fleet.domain.enums.CarType;
import com.realdolmen.fleet.domain.enums.FuelType;
import com.realdolmen.fleet.domain.enums.RimType;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
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

    @Column(nullable = false)
    private LocalDate productionDate;

    @Min(value = 0)
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

    @Min(value = 0)
    private int emission;

    private boolean active;

    @Enumerated(EnumType.STRING)
    private RimType rimType;

    private int idealKm;
    private int maxKm;
    private double listPrice;
    private double monthlyBenefit;
    private double upgradeAmount;
    private double downgradeAmount;
    private int hPower;
    private String engine;


    @ManyToOne
    private FunctionalLevel category;

    private String thumbnail;

    /* Used by JPA */
    public Car() {
    }

    public int getIdealKm() {
        return idealKm;
    }

    public void setIdealKm(int idealKm) {
        this.idealKm = idealKm;
    }

    public int getMaxKm() {
        return maxKm;
    }

    public void setMaxKm(int maxKm) {
        this.maxKm = maxKm;
    }

    public double getListPrice() {
        return listPrice;
    }

    public void setListPrice(double listPrice) {
        this.listPrice = listPrice;
    }

    public double getMonthlyBenefit() {
        return monthlyBenefit;
    }

    public void setMonthlyBenefit(double monthlyBenefit) {
        this.monthlyBenefit = monthlyBenefit;
    }

    public double getUpgradeAmount() {
        return upgradeAmount;
    }

    public void setUpgradeAmount(double upgradeAmount) {
        this.upgradeAmount = upgradeAmount;
    }

    public double getDowngradeAmount() {
        return downgradeAmount;
    }

    public void setDowngradeAmount(double downgradeAmount) {
        this.downgradeAmount = downgradeAmount;
    }

    public int gethPower() {
        return hPower;
    }

    public void sethPower(int hPower) {
        this.hPower = hPower;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public RimType getRimType() {
        return rimType;
    }

    public void setRimType(RimType rimType) {
        this.rimType = rimType;
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

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }

    public int getFiscalHorsePower() {
        return fiscalHorsePower;
    }

    public void setFiscalHorsePower(int fiscalHorsePower) {
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

    public boolean isHybrid() {
        return hybrid;
    }

    public void setHybrid(boolean hybrid) {
        this.hybrid = hybrid;
    }

    public int getEmission() {
        return emission;
    }

    public void setEmission(int emission) {
        this.emission = emission;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
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

    //Check if this is for New of Update
    public boolean isNew() {
        return (this.id == null);
    }
}
