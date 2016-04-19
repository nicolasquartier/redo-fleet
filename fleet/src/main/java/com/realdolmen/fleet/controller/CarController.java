package com.realdolmen.fleet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/cars")
public class CarController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String carcatalog() {
        return "carcatalog";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String cardetail(@PathVariable("id") Long id) {
        return "cardetail";
    }

    @RequestMapping(value = "/mycar", method = RequestMethod.GET)
    public String mycar() {
        return "mycar";
    }
}
