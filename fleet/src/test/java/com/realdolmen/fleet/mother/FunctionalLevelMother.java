package com.realdolmen.fleet.mother;

import com.realdolmen.fleet.domain.FunctionalLevel;

public class FunctionalLevelMother {

    FunctionalLevel functionalLevel;

    public FunctionalLevelMother() {
        this.functionalLevel = new FunctionalLevel();
        this.functionalLevel.setFLevel(1);
    }

    public FunctionalLevel build() {
        return this.functionalLevel;
    }

    public static FunctionalLevelMother init() {
        return new FunctionalLevelMother();
    }
}