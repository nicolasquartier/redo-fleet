package com.realdolmen.fleet.mother;

import com.realdolmen.fleet.domain.Car;
import com.realdolmen.fleet.domain.FunctionalLevel;
import com.realdolmen.fleet.domain.Option;
import com.realdolmen.fleet.domain.enums.OptionType;

public class OptionMother {

    Option option;

    public OptionMother() {
        this.option = new Option();
        this.option.setDescription("The mother of all options");
        this.option.setType(OptionType.DEALER);
    }

    public void setCar(Car car) {
        this.option.setCar(car);
    }

    public Option build() {
        return this.option;
    }

    public static OptionMother init() {
        return new OptionMother();
    }
}
