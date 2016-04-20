package com.realdolmen.fleet.controller;

import com.realdolmen.fleet.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/cars")
public class CarController {

    @Autowired
    CarRepository carRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String carcatalog() {
        return "carcatalog";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getCarDetail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("car", carRepository.findOne(id));
        return "cardetail";
    }

    @RequestMapping(value = "{id}/configure", method = RequestMethod.GET)
    public String cardetailConfigureOptions(@PathVariable("id") Long id, Model model) {
        return "carcatalog";
    }

    @RequestMapping(value = "/mycar", method = RequestMethod.GET)
    public String mycar() {
        return "mycar";
    }
}
