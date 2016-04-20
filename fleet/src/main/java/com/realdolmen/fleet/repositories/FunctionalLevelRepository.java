package com.realdolmen.fleet.repositories;

import com.realdolmen.fleet.domain.FunctionalLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface FunctionalLevelRepository extends CrudRepository<FunctionalLevel, Long> {
    FunctionalLevel findByFLevel(Integer fLevel);
}
