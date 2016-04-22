package com.realdolmen.fleet.controller;

import com.realdolmen.fleet.domain.Authorities;
import com.realdolmen.fleet.repository.AuthoritiesRepository;
import com.realdolmen.fleet.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private AuthoritiesRepository authoritiesRepository;

    @Autowired
    private AuthService authentication;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getEmptyLoginForm(Model model,
                                    @RequestParam(value = "error", required = false) String error,
                                    @RequestParam(value = "logout", required = false) String logout) {

        model.addAttribute("username", "");
        model.addAttribute("password", "");
        model.addAttribute("loginError", "");
        model.addAttribute("logoutMessage", "");

        if (logout != null) {
            model.addAttribute("logoutMessage", "Successfully logged out!");
        }

        if (error != null) {
            model.addAttribute("loginError", "Invalid username or password");
            return "login";
        }

        Authorities auth = authoritiesRepository.findByUsername(authentication.getName());

        if (auth == null) {
            return "login";
        }
        if (auth.getAuthority().equals("ROLE_ADMIN")) {
            return "redirect:/admin";
        } else if (auth.getAuthority().equals("ROLE_USER")) {
            return "redirect:/cars";
        }
        return "login";
    }
}
