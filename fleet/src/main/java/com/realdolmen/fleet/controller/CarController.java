package com.realdolmen.fleet.controller;

import com.realdolmen.fleet.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.realdolmen.fleet.domain.Car;
import com.realdolmen.fleet.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String carcatalog(Model model) {
        List<Car> activeCars = carRepository.findByActive(true);
        model.addAttribute("cars", activeCars);
        return "carcatalog";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getCarDetail(@PathVariable("id") Long id, Model model) {
        Car car = carRepository.findOne(id);
        model.addAttribute("car", carRepository.findOne(id));
        return "cardetail";
    }

    @RequestMapping(value = "/mycar", method = RequestMethod.GET)
    public String mycar() {
        return "mycar";
    }
}
