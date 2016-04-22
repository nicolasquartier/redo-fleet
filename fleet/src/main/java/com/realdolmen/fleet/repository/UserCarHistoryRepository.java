package com.realdolmen.fleet.repository;

import com.realdolmen.fleet.domain.User;
import com.realdolmen.fleet.domain.UserCarHistory;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.TemporalType;
import java.util.Date;

@Repository
public interface UserCarHistoryRepository extends CrudRepository<UserCarHistory, Long> {

    UserCarHistory findByUserAndEndDateAfter(User user, @Temporal(TemporalType.DATE) Date endDate);

}
