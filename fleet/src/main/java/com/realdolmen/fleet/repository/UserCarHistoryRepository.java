package com.realdolmen.fleet.repository;

import com.realdolmen.fleet.domain.User;
import com.realdolmen.fleet.domain.UserCarHistory;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.TemporalType;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface UserCarHistoryRepository extends CrudRepository<UserCarHistory, Long> {

    UserCarHistory findByUserAndEndDateAfter(User user, LocalDate endDate);
    List<UserCarHistory>  findByUserAndEndDateBefore(User currentUser, LocalDate endDate);
    List<UserCarHistory> findAllByCompanyCarApprovedFalse();
    List<UserCarHistory> findAllByCompanyCarApprovedTrue();

}
