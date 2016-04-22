package com.realdolmen.fleet.service.impl;

import com.realdolmen.fleet.domain.Car;
import com.realdolmen.fleet.domain.CompanyCar;
import com.realdolmen.fleet.domain.Option;
import com.realdolmen.fleet.domain.UserCarHistory;
import com.realdolmen.fleet.repository.UserCarHistoryRepository;
import com.realdolmen.fleet.service.OrderFlowService;
import com.realdolmen.fleet.vo.OrderViewObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderFlowServiceImpl implements OrderFlowService {

    @Autowired
    private CompanyCarServiceImpl companyCarService;

    @Autowired
    private UserCarHistoryRepository userCarHistoryRepository;

    @Autowired
    private AuthServiceImpl authService;

    @Override
    public void save(OrderViewObject orderVO) {
        UserCarHistory userCarHistory = orderVO.getUserCarHistory();
        CompanyCar companyCar = orderVO.getCompanyCar();
        Car car = orderVO.getCar();
        List<Option> options = orderVO.getOptions();

        saveCompanyCar(companyCar, car, options);
        saveHistory(userCarHistory, companyCar);
    }

    private void saveCompanyCar(CompanyCar companyCar, Car car, List<Option> options) {
        companyCarService.createCompanyCarWithOptions(companyCar, car, options);
    }

    private void saveHistory(UserCarHistory userCarHistory, CompanyCar companyCar) {
        userCarHistoryRepository.save(userCarHistory);
        userCarHistory.setUser(authService.getCurrentUser());
        userCarHistory.setCompanyCar(companyCar);
        userCarHistoryRepository.save(userCarHistory);
    }

    void setAuthenticationService(AuthServiceImpl authenticationService) {
        this.authService = authenticationService;
    }
}
