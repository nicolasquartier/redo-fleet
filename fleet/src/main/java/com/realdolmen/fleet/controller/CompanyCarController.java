package com.realdolmen.fleet.controller;

import com.realdolmen.fleet.domain.Car;
import com.realdolmen.fleet.domain.CompanyCar;
import com.realdolmen.fleet.domain.Option;
import com.realdolmen.fleet.repository.CompanyCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CompanyCarController {

    @Autowired
    private CompanyCarRepository companyCarRepository;

    public CompanyCar createCompanyCar(Car car, List<Option> options) {
        CompanyCar companyCar = new CompanyCar();
        companyCar.setCar(car);
        companyCar.setOptions(options);
        companyCarRepository.save(companyCar);
        return companyCar;
    }
}
