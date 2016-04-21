package com.realdolmen.fleet.repositories;

import com.realdolmen.fleet.domain.Authorities;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthoritiesRepository extends CrudRepository<Authorities, Long> {

    Authorities findByUsername(String username);
}
