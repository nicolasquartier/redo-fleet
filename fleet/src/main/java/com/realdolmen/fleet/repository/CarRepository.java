package com.realdolmen.fleet.repository;

import com.realdolmen.fleet.domain.Car;
import com.realdolmen.fleet.domain.FunctionalLevel;
import com.realdolmen.fleet.domain.enums.Brand;
import com.realdolmen.fleet.domain.enums.CarType;
import com.realdolmen.fleet.domain.enums.FuelType;
import com.realdolmen.fleet.domain.enums.RimType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByActive(Boolean active);

    List<Car> findByCategoryAndTypeAndBrand(FunctionalLevel category, CarType type, Brand brand);

    List<Car> findByTypeAndBrand(CarType type, Brand brand);

    List<Car> findByCategoryAndType(FunctionalLevel category, CarType type);

    List<Car> findByCategoryAndBrand(FunctionalLevel category, Brand brand);

    List<Car> findByCategory(FunctionalLevel category);

    List<Car> findByType(CarType type);

    List<Car> findByBrand(Brand brand);

    @Modifying
    @Query("UPDATE Car car SET car.active=?1,car.brand=?2,car.emission=?3,car.fiscalHorsePower=?4," +
            "car.fuelType=?5,car.hybrid=?6,car.model=?7,car.pack=?8,car.productionDate=?9,car.rimType=?10," +
            "car.thumbnail=?11,car.type=?12,car.version=?13,car.category=?14,car.downgradeAmount=?15," +
            "car.engine=?16,car.hPower=?17,car.idealKm=?18,car.listPrice=?19,car.maxKm=?20,car.monthlyBenefit=?21," +
            "car.upgradeAmount=?22 WHERE car.id=?23")
    void setCarInfoById(Boolean active, Brand brand, Integer emission, Integer fiscalHorsePower, FuelType fuelType, Boolean hybrid,
                        String model, String pack, LocalDate productionDate, RimType rimType,
                        String thumbnail, CarType carType, Long version, FunctionalLevel category, Double downgradeAmount,
                        String engine, Integer hPower, Integer idealKm, Double listPrice, Integer maxKm, Double monthlyBenefit,
                        Double upgradeAmount, Long id);

}
