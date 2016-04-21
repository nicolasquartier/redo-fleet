package com.realdolmen.fleet.repositories;

import com.realdolmen.fleet.domain.UserCarHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCarHistoryRepository extends CrudRepository<UserCarHistory, Long> {
}
