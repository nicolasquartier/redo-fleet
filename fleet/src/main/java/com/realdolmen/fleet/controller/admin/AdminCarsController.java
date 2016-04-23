package com.realdolmen.fleet.controller.admin;

import com.realdolmen.fleet.domain.Car;
import com.realdolmen.fleet.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaOptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;


/*

@Controller
@RequestMapping("/admin/cars")
public class AdminCarsController {

    private CarRepository carRepository;

    @Autowired
    public AdminCarsController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String adminCarOverview(Model model) {
        List<Car> adminCars = carRepository.findAll();
        model.addAttribute("cars", adminCars);
        return "admin/allcars";
    }

   @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showAdminEditCar(@PathVariable("id") long id, Model model) {
        return "/admin/caredit";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String adminEditCar(
            @Valid Car car,
            BindingResult bindingResult,
            HttpServletRequest request
    ) {

        if(!bindingResult.hasErrors()) {
                carRepository.save(car);
        }

        if(bindingResult.hasErrors()) {
            return "/admin/caredit";
            //return "redirect:" + request.getRequestURI();
        }

        return "redirect:/admin/cars/";
        //return "redirect:" + request.getRequestURI();
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String adminCreateCar() {
        return "admin/createcar";
    }

}
*/
