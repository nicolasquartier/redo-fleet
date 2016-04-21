package com.realdolmen.fleet.mother;

import com.realdolmen.fleet.domain.CompanyCar;

import java.util.Date;

public class CompanyCarMother {

    private CompanyCar companyCar;

    private CompanyCarMother() {
        this.companyCar = new CompanyCar();
        companyCar.setActive(true);
        companyCar.setApproved(false);
        companyCar.setCar(CarMother.init().build());
        companyCar.setConcession("DON BOSCO Halle");
        companyCar.setDeliveryDate(new Date(System.currentTimeMillis() + 2_000_000));
        companyCar.setOrderDate(new Date(System.currentTimeMillis() - 2_000_000));
    }

    public static CompanyCarMother init() {
        return new CompanyCarMother();
    }

    public CompanyCar build() {
        return this.companyCar;
    }

}
