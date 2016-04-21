package com.realdolmen.fleet.mother;

import com.realdolmen.fleet.domain.UserCarHistory;

import java.util.Date;

public class UserCarHistoryMother {

    private UserCarHistory userCarHistory;

    private UserCarHistoryMother() {
        this.userCarHistory = new UserCarHistory();
        userCarHistory.setChoosenLevel(0);
        userCarHistory.setCompanyCar(CompanyCarMother.init().build());
        userCarHistory.setEndDate(new Date(System.currentTimeMillis() + 6_000_000));
        userCarHistory.setStartDate(new Date(System.currentTimeMillis() - 6_000_000));
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
