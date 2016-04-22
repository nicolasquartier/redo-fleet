package com.realdolmen.fleet.mother;


import com.realdolmen.fleet.domain.Car;
import com.realdolmen.fleet.domain.FunctionalLevel;
import com.realdolmen.fleet.domain.enums.Brand;
import com.realdolmen.fleet.domain.enums.CarType;
import com.realdolmen.fleet.domain.enums.FuelType;

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
        car.setProductionDate(new Date());
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
