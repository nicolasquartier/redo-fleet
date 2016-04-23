package com.realdolmen.fleet.controller.admin;

import com.realdolmen.fleet.domain.Car;
import com.realdolmen.fleet.repository.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class CarAdminController {

    private final Logger logger = LoggerFactory.getLogger(CarAdminController.class);

    @Autowired
    private CarRepository carRepository;

    @RequestMapping(value = "/admin/", method = RequestMethod.GET)
    public String index(Model model) {
        logger.debug("index()");
        return "redirect:/admin/cars";
    }

    // list page
    @RequestMapping(value = "/admin/cars", method = RequestMethod.GET)
    public String showAllCars(Model model) {

        logger.debug("showAllCars()");
        model.addAttribute("cars", carRepository.findAll());
        return "/admin/allcars";
    }

    // save or update user
    // 1. @ModelAttribute bind form value
    // 2. @Validated form validator
    // 3. RedirectAttributes for flash value
    @RequestMapping(value = "/admin/cars", method = RequestMethod.POST)
    public String saveOrUpdateUser(@ModelAttribute("caredit") @Validated Car car,
                                   BindingResult result, Model model,
                                   final RedirectAttributes redirectAttributes) {

        logger.debug("saveOrUpdateCar() : {}", car);

        if (result.hasErrors()) {
            //populateDefaultModel(model);
            return "/admin/caredit";
        } else {

            // Add message to flash scope
            redirectAttributes.addFlashAttribute("css", "success");
            if (car.isNew()) {
                redirectAttributes.addFlashAttribute("msg", "Car added successfully!");
            } else {
                redirectAttributes.addFlashAttribute("msg", "Car updated successfully!");
            }

            carRepository.save(car);

            // POST/REDIRECT/GET
            return "redirect:/admin/cars/" + car.getId();

            // POST/FORWARD/GET
            // return "user/list";

        }

    }

    // show add car form
    @RequestMapping(value = "/admin/cars/add", method = RequestMethod.GET)
    public String showAddUserForm(Model model) {

        logger.debug("showAddCarForm()");

        Car car = new Car();

        return "/admin/createcar";

    }

    // show update form
    @RequestMapping(value = "/admin/cars/{id}", method = RequestMethod.GET)
    public String showUpdateUserForm(@PathVariable("id") long id, Model model) {

        logger.debug("showUpdateCarForm() : {}", id);

        Car car = carRepository.findOne(id);
        model.addAttribute("caredit", car);

        //populateDefaultModel(model);

        return "/admin/caredit";

    }

    // delete user
/*    @RequestMapping(value = "/admin/cars/{id}/delete", method = RequestMethod.POST)
    public String deleteUser(@PathVariable("id") long id,
                             final RedirectAttributes redirectAttributes) {

        logger.debug("deleteCar() : {}", id);

        carRepository.delete(id);

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Car is deleted!");

        return "redirect:/admin/allcars";

    }*/

    // show user
/*    @RequestMapping(value = "/admin/cars/{id}", method = RequestMethod.GET)
    public String showUser(@PathVariable("id") long id, Model model) {

        logger.debug("showcar() id: {}", id);

        Car car = carRepository.findOne(id);
        if (car == null) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", "User not found");
        }
        model.addAttribute("car", car);

        return "/admin/caredit";

    }*/
}
