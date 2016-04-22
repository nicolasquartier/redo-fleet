package com.realdolmen.fleet.service;

import com.realdolmen.fleet.domain.Car;
import com.realdolmen.fleet.domain.CompanyCar;
import com.realdolmen.fleet.domain.Option;

import java.util.List;

public interface CompanyCarService {

    CompanyCar createCompanyCarWithOptions(Car car, List<Option> options);

}
