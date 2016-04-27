package com.realdolmen.fleet.repository;

import com.realdolmen.fleet.domain.User;
import com.realdolmen.fleet.domain.UserCarHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface UserCarHistoryRepository extends CrudRepository<UserCarHistory, Long> {

    UserCarHistory findByUserAndEndDateAfter(User user, LocalDate endDate);

}
