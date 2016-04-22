package com.realdolmen.fleet.mother;

import com.realdolmen.fleet.domain.FunctionalLevel;

public class FunctionalLevelMother {

    private FunctionalLevel functionalLevel;

    private FunctionalLevelMother() {
        this.functionalLevel = new FunctionalLevel();
        this.functionalLevel.setFLevel(1);
    }

    public static FunctionalLevelMother init() {
        return new FunctionalLevelMother();
    }

    public FunctionalLevel build() {
        return this.functionalLevel;
    }
}
