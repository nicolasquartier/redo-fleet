package com.realdolmen.fleet.controller.admin;

import com.realdolmen.fleet.domain.Car;
import com.realdolmen.fleet.domain.User;
import com.realdolmen.fleet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class AdminUsersController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showCarsOverview(Model model) {
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "admin/allusers";
    }
}
