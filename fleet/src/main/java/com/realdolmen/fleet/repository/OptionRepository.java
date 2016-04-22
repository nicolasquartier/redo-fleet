package com.realdolmen.fleet.repository;

import com.realdolmen.fleet.domain.Car;
import com.realdolmen.fleet.domain.Option;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OptionRepository extends CrudRepository<Option, Long> {
    List<Option> findByCar(Car car);
}
