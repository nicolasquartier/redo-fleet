package com.realdolmen.fleet.controller.admin;

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
@RequestMapping("/admin/cars")
public class AdminCarsController {

    @Autowired
    private CarRepository carRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String adminCarOverview(Model model) {
        List<Car> adminCars = carRepository.findAll();
        model.addAttribute("cars", adminCars);
        return "admin/allcars";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String adminEditCar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("car", carRepository.findOne(id));
        return "admin/caredit";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String adminCreateCar() {
        return "admin/createcar";
    }
}
