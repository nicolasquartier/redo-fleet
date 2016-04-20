package com.realdolmen.fleet.repositories;

import com.realdolmen.fleet.domain.Car;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
}
