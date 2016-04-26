package com.realdolmen.fleet.domain;

import com.realdolmen.fleet.domain.enums.Brand;
import com.realdolmen.fleet.domain.enums.CarType;
import com.realdolmen.fleet.domain.enums.FuelType;
import com.realdolmen.fleet.domain.enums.RimType;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.*;

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
    private Integer fiscalHorsePower;

    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    @Column(length = 350)
    @Size(max = 350)
    private String pack;

    @Enumerated(EnumType.STRING)
    private CarType type;

    private boolean hybrid;

    @Min(value = 0)
    private Integer emission;

    private Boolean active;

    @Enumerated(EnumType.STRING)
    private RimType rimType;

    private Integer idealKm;
    private Integer maxKm;
    private Double listPrice;
    private Double monthlyBenefit;
    private Double upgradeAmount;
    private Double downgradeAmount;
    private Integer hPower;
    private String engine;

    @ManyToMany
    private List<Option> options = new ArrayList<>();

    @ManyToOne
    private FunctionalLevel category;

    private String thumbnail;

    /* Used by JPA */
    public Car() {
    }

    public Integer getIdealKm() {
        return idealKm;
    }

    public void setIdealKm(Integer idealKm) {
        this.idealKm = idealKm;
    }

    public Integer getMaxKm() {
        return maxKm;
    }

    public void setMaxKm(Integer maxKm) {
        this.maxKm = maxKm;
    }

    public Double getListPrice() {
        return listPrice;
    }

    public void setListPrice(Double listPrice) {
        this.listPrice = listPrice;
    }

    public Double getMonthlyBenefit() {
        return monthlyBenefit;
    }

    public void setMonthlyBenefit(Double monthlyBenefit) {
        this.monthlyBenefit = monthlyBenefit;
    }

    public Double getUpgradeAmount() {
        return upgradeAmount;
    }

    public void setUpgradeAmount(Double upgradeAmount) {
        this.upgradeAmount = upgradeAmount;
    }

    public Double getDowngradeAmount() {
        return downgradeAmount;
    }

    public void setDowngradeAmount(Double downgradeAmount) {
        this.downgradeAmount = downgradeAmount;
    }

    public Integer getHPower() {
        return hPower;
    }

    public void setHPower(Integer hPower) {
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

    public List<Option> getOptions() {
        return Collections.unmodifiableList(options);
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public void addOption(Option option) {
        this.options.add(option);
    }

    public void removeOption(Option option) {
        this.options.remove(option);
    }

}
