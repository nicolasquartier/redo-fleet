package com.realdolmen.fleet.vo;


import com.realdolmen.fleet.domain.Car;
import com.realdolmen.fleet.domain.CompanyCar;
import com.realdolmen.fleet.domain.Option;
import com.realdolmen.fleet.domain.UserCarHistory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderViewObject implements Serializable {

    private CompanyCar companyCar;
    private Car car;
    private List<Option> options;
    private UserCarHistory userCarHistory;

    public OrderViewObject() {
        this.companyCar = new CompanyCar();
        this.car = new Car();
        this.options = new ArrayList<Option>();
        this.userCarHistory = new UserCarHistory();
    }

//    public OrderViewObject() {
//
//    }

//    public OrderViewObject(CompanyCar companyCar, Car car, List<Option> options, UserCarHistory userCarHistory) {
//        this.companyCar = companyCar;
//        this.car = car;
//        this.options = options;
//        this.userCarHistory = userCarHistory;
//    }

    public CompanyCar getCompanyCar() {
        return companyCar;
    }

    public void setCompanyCar(CompanyCar companyCar) {
        this.companyCar = companyCar;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public UserCarHistory getUserCarHistory() {
        return userCarHistory;
    }

    public void setUserCarHistory(UserCarHistory userCarHistory) {
        this.userCarHistory = userCarHistory;
    }
}
