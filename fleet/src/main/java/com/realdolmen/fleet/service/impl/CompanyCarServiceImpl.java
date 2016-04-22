package com.realdolmen.fleet.service.impl;

import com.realdolmen.fleet.domain.Car;
import com.realdolmen.fleet.domain.CompanyCar;
import com.realdolmen.fleet.domain.Option;
import com.realdolmen.fleet.repository.CompanyCarRepository;
import com.realdolmen.fleet.repository.OptionRepository;
import com.realdolmen.fleet.service.CompanyCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CompanyCarServiceImpl implements CompanyCarService {

    @Autowired
    private CompanyCarRepository companyCarRepository;

    @Autowired
    private OptionRepository optionRepository;

    public CompanyCar createCompanyCarWithOptions(Car car, List<Option> options) {
        CompanyCar companyCar = new CompanyCar();

        linkCar(car, companyCar);
        linkOptions(options, companyCar);

        return companyCar;
    }

    private void linkCar(Car car, CompanyCar companyCar) {
        companyCar.setCar(car);
        companyCarRepository.save(companyCar);
    }

    private void linkOptions(List<Option> options, CompanyCar companyCar) {
        options.forEach(companyCar::addOption);
        options.forEach(option -> option.addCompanyCar(companyCar));
        companyCarRepository.save(companyCar);
    }
}
