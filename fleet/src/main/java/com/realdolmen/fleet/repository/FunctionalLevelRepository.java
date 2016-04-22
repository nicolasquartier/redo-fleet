package com.realdolmen.fleet.repository;

import com.realdolmen.fleet.domain.FunctionalLevel;
import org.springframework.data.repository.CrudRepository;


public interface FunctionalLevelRepository extends CrudRepository<FunctionalLevel, Long> {
    FunctionalLevel findByFLevel(Integer fLevel);
}
