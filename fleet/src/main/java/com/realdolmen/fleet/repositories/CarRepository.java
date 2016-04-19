package com.realdolmen.fleet.repositories;

import com.realdolmen.fleet.domain.FunctionalLevel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CarRepository extends JpaRepository<FunctionalLevel, Long> {
}
