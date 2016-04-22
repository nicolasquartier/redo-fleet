package com.realdolmen.fleet.service.impl;

import com.realdolmen.fleet.domain.Car;
import com.realdolmen.fleet.domain.CompanyCar;
import com.realdolmen.fleet.domain.Option;
import com.realdolmen.fleet.repository.CompanyCarRepository;
import com.realdolmen.fleet.repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompanyCarServiceImpl {

    @Autowired
    private CompanyCarRepository companyCarRepository;

    @Autowired
    private OptionRepository optionRepository;

    @Transactional
    public void createCompanyCarWithOptions(CompanyCar companyCar, Car car, List<Option> options) {
        companyCarRepository.save(companyCar);
        linkCar(car, companyCar);
        companyCarRepository.save(companyCar);
        linkOptions(options, companyCar);
        companyCarRepository.save(companyCar);
    }

    private void linkCar(Car car, CompanyCar companyCar) {
        companyCar.setCar(car);
    }

    private void linkOptions(List<Option> options, CompanyCar companyCar) {
        options.forEach(companyCar::addOption);
        options.forEach(option -> option.addCompanyCar(companyCar));
    }
}
