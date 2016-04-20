package com.realdolmen.fleet.repositories;

import com.realdolmen.fleet.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByActive(Boolean active);
}
