package com.realdolmen.fleet.mother;


import com.realdolmen.fleet.domain.Car;
import com.realdolmen.fleet.domain.FunctionalLevel;
import com.realdolmen.fleet.domain.enums.Brand;
import com.realdolmen.fleet.domain.enums.CarType;
import com.realdolmen.fleet.domain.enums.FuelType;
import com.realdolmen.fleet.domain.enums.RimType;

import java.time.LocalDate;
import java.util.Date;

public class CarMother {

    Car car;

    private CarMother() {
        this.car = new Car();
        car.setActive(true);
        car.setBrand(Brand.AUDI);
        car.setEmission(100);
        car.setFuelType(FuelType.DIESEL);
        car.setType(CarType.BERLINE);
        car.setFiscalHorsePower(10);
        car.setHybrid(false);
        car.setRimType(RimType.ALUMINIUM);
        car.setIdealKm(20000);
        car.setMaxKm(200000);
        car.setListPrice(25000);
        car.setMonthlyBenefit(200);
        car.setUpgradeAmount(500);
        car.setDowngradeAmount(500);
        car.setEngine("vroemmmm");
        car.sethPower(115);
        car.setProductionDate(LocalDate.now());
    }

    public static CarMother init() {
        return new CarMother();
    }

    public CarMother setCategoryOrFunctionLevel(FunctionalLevel category) {
        this.car.setCategory(category);
        return this;
    }

    public Car build() {
        return this.car;
    }
}
