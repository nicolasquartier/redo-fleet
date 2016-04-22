package com.realdolmen.fleet.repository;

import com.realdolmen.fleet.domain.CompanyCar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyCarRepository extends CrudRepository<CompanyCar, Long> {

}
