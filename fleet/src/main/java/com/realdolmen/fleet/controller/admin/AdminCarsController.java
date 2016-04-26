package com.realdolmen.fleet.controller.admin;

import com.realdolmen.fleet.domain.Car;
import com.realdolmen.fleet.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaOptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/cars")
public class AdminCarsController {

    private CarRepository carRepository;

    @Autowired
    public AdminCarsController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showCarsOverview(Model model) {
        List<Car> adminCars = carRepository.findAll();
        model.addAttribute("cars", adminCars);
        return "admin/allcars";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showAdminEditCar(@PathVariable Long id, Model model) {
        Car car =  carRepository.findOne(id);
        model.addAttribute(car);
        return "/admin/caredit";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @Transactional
    public String adminEditCar(@PathVariable Long id, @Valid Car car, Errors errors) {
        if(errors.hasErrors()) {
            return "/admin/caredit";
        }
        carRepository.setCarInfoById(car.getActive(),car.getBrand(),car.getEmission(),car.getFiscalHorsePower(),car.getFuelType(),
                car.getHybrid(),car.getModel(),car.getPack(),car.getProductionDate(),car.getRimType(),car.getThumbnail(),car.getType(),
                car.getVersion(),car.getCategory(),car.getDowngradeAmount(),car.getEngine(),car.getHPower(),car.getIdealKm(),
                car.getListPrice(),car.getMaxKm(),car.getMonthlyBenefit(),car.getUpgradeAmount(),car.getId());
        return "redirect:/admin/cars";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String adminCreateCar(Model model) {
        Car car = new Car();
        model.addAttribute(car);

        return "/admin/createcar";
    }
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String adminCreateCar(@Valid Car car, Errors errors) {
        if(errors.hasErrors()) {
            return "/admin/createcar";
        }

        carRepository.save(car);

        return "redirect:/admin/cars";
    }
}
