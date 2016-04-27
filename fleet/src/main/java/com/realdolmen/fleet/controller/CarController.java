package com.realdolmen.fleet.controller;

import com.realdolmen.fleet.domain.Car;
import com.realdolmen.fleet.domain.FunctionalLevel;
import com.realdolmen.fleet.domain.enums.Brand;
import com.realdolmen.fleet.domain.enums.CarType;
import com.realdolmen.fleet.repository.CarRepository;
import com.realdolmen.fleet.repository.FunctionalLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private FunctionalLevelRepository functionalLevelRepository;

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public String carCatalogFiltered(
            @RequestParam(value = "categoryLevel", required = false, defaultValue = "0") Integer categoryLevel,
            @RequestParam(value = "type", required = false, defaultValue = "ALL") String typeStr,
            @RequestParam(value = "brand", required = false, defaultValue = "ALL") String brandStr,
            Model model) {

        List<Car> filteredCars = filterCarsWithFilterOptions(categoryLevel, typeStr, brandStr);

        if(filteredCars.size() > 0) {
            model.addAttribute("cars", filteredCars);
        }
        else {
            model.addAttribute("noFilteredCarsFound", "There were no cars found with this filter settings.");
            model.addAttribute("wantedCategoryLevel", categoryLevel.toString());
            model.addAttribute("wantedType", typeStr);
            model.addAttribute("wantedBrand", brandStr);
        }
        return "carcatalog";
    }

    private List<Car> filterCarsWithFilterOptions(Integer categoryLevel, String typeStr, String brandStr) {
        CarType type = CarType.BERLINE;//just some default
        Brand brand = Brand.AUDI;//just some default
        FunctionalLevel category = functionalLevelRepository.findByFLevel(categoryLevel);

        if(category != null){
            if(!typeStr.equals("ALL") && !brandStr.equals("ALL")) {
                type = Enum.valueOf(CarType.class, typeStr);
                brand = Enum.valueOf(Brand.class, brandStr);
                return carRepository.findByCategoryAndTypeAndBrand(category, type, brand);
            }
            else if(!typeStr.equals("ALL") && brandStr.equals("ALL")) {
                type = Enum.valueOf(CarType.class, typeStr);
                return carRepository.findByCategoryAndType(category, type);

            }
            else if(typeStr.equals("ALL") && !brandStr.equals("ALL")) {
                brand = Enum.valueOf(Brand.class, brandStr);
                return carRepository.findByCategoryAndBrand(category, brand);
            }
            else if(typeStr.equals("ALL") && brandStr.equals("ALL")) {
                return carRepository.findByCategory(category);
            }
        }
        else if(category == null) {
            if(!typeStr.equals("ALL") && !brandStr.equals("ALL")) {
                type = Enum.valueOf(CarType.class, typeStr);
                brand = Enum.valueOf(Brand.class, brandStr);
                return carRepository.findByTypeAndBrand(type, brand);
            }
            else if(!typeStr.equals("ALL") && brandStr.equals("ALL")) {
                type = Enum.valueOf(CarType.class, typeStr);
                return carRepository.findByType(type);

            }
            else if(typeStr.equals("ALL") && !brandStr.equals("ALL")) {
                brand = Enum.valueOf(Brand.class, brandStr);
                return carRepository.findByBrand(brand);
            }
            else if(typeStr.equals("ALL") && brandStr.equals("ALL")) {
                return carRepository.findByActive(true);
            }
        }

        return new ArrayList<Car>();
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String carcatalog(Model model) {
        List<Car> activeCars = carRepository.findByActive(true);
        model.addAttribute("cars", activeCars);
        return "carcatalog";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getCarDetail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("car", carRepository.findOne(id));
        return "cardetail";
    }
}
