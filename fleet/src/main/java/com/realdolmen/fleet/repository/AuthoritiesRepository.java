package com.realdolmen.fleet.repository;

import com.realdolmen.fleet.domain.Authorities;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthoritiesRepository extends CrudRepository<Authorities, Long> {

    Authorities findByUsername(String username);
}
