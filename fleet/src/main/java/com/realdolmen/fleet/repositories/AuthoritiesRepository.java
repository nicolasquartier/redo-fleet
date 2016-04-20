package com.realdolmen.fleet.repositories;

import com.realdolmen.fleet.domain.Authorities;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthoritiesRepository extends CrudRepository<Authorities, Long> {

    Authorities findByUsername(String username);
}
