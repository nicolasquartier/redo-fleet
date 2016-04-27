package com.realdolmen.fleet.mother;

import com.realdolmen.fleet.domain.UserCarHistory;

import java.time.LocalDate;

public class UserCarHistoryMother {

    private UserCarHistory userCarHistory;

    private UserCarHistoryMother() {
        this.userCarHistory = new UserCarHistory();
        userCarHistory.setChoosenLevel(0);
        userCarHistory.setCompanyCar(CompanyCarMother.init().build());
        userCarHistory.setEndDate(LocalDate.now().plusYears(2));
        userCarHistory.setStartDate(LocalDate.now().minusYears(2));
        userCarHistory.setFunctionalLevelCopy(4);
        userCarHistory.setEstimatedMileage(160000);
        userCarHistory.setReplacement(true);
        userCarHistory.setUser(UserMother.init().build());
    }

    public static UserCarHistoryMother init() {
        return new UserCarHistoryMother();
    }

    public UserCarHistory build() {
        return this.userCarHistory;
    }
}
