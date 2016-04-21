package com.realdolmen.fleet.mother;

import com.realdolmen.fleet.domain.Car;
import com.realdolmen.fleet.domain.FunctionalLevel;
import com.realdolmen.fleet.domain.Option;
import com.realdolmen.fleet.domain.enums.OptionType;

public class OptionMother {

    Option option;

    private OptionMother() {
        this.option = new Option();
        this.option.setDescription("The mother of all options");
        this.option.setType(OptionType.DEALER);
        this.option.setCar(CarMother.init().build());
    }

    public Option build() {
        return this.option;
    }

    public static OptionMother init() {
        return new OptionMother();
    }
}
