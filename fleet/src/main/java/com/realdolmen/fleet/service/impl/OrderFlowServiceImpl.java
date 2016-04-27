package com.realdolmen.fleet.service.impl;

import com.realdolmen.fleet.domain.Car;
import com.realdolmen.fleet.domain.CompanyCar;
import com.realdolmen.fleet.domain.Option;
import com.realdolmen.fleet.domain.UserCarHistory;
import com.realdolmen.fleet.repository.UserCarHistoryRepository;
import com.realdolmen.fleet.vo.OrderViewObject;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderFlowServiceImpl {

    @Autowired
    private CompanyCarServiceImpl companyCarService;

    @Autowired
    private UserCarHistoryRepository userCarHistoryRepository;

    @Autowired
    private AuthServiceImpl authService;

    @Transactional
    public void save(OrderViewObject orderVO) {
        UserCarHistory userCarHistory = orderVO.getUserCarHistory();
        CompanyCar companyCar = orderVO.getCompanyCar();
        Car car = orderVO.getCar();
        List<Option> options = orderVO.getOptions();

        saveCompanyCar(companyCar, car, options);
        saveHistory(userCarHistory, companyCar);
    }


    private void saveCompanyCar(CompanyCar companyCar, Car car, List<Option> options) {
        companyCar.setApproved(false);
        companyCar.setActive(true);
        companyCar.setOrderDate(LocalDate.now());
        companyCarService.createCompanyCarWithOptions(companyCar, car, options);
    }

    private void saveHistory(UserCarHistory userCarHistory, CompanyCar companyCar) {

        userCarHistory.setStartDate(LocalDate.now());
        userCarHistory.setEndDate(getDatePlusFourYear());
        userCarHistory.setChoosenLevel(calculateChosenLevelFor(companyCar));
        userCarHistory.setFunctionalLevelCopy(authService.getCurrentUser().getCategory());
//        userCarHistoryRepository.save(userCarHistory);

        linkUserAndCompanyCarToUSH(userCarHistory, companyCar);
    }

    private Integer calculateChosenLevelFor(CompanyCar companyCar) {
        return authService.getCurrentUser().getCategory() - companyCar.getCategory();
    }

    private void linkUserAndCompanyCarToUSH(UserCarHistory userCarHistory, CompanyCar companyCar) {
        userCarHistory.setUser(authService.getCurrentUser());
        userCarHistory.setCompanyCar(companyCar);
        userCarHistoryRepository.save(userCarHistory);
    }

    @NotNull
    private LocalDate getDatePlusFourYear() {
        return LocalDate.now().plusYears(4);
    }

    void setAuthenticationService(AuthServiceImpl authenticationService) {
        this.authService = authenticationService;
    }
}
