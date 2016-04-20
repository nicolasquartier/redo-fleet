package com.realdolmen.fleet.controller;

import com.realdolmen.fleet.domain.Authorities;
import com.realdolmen.fleet.domain.User;
import com.realdolmen.fleet.repositories.AuthoritiesRepository;
import com.realdolmen.fleet.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private AuthoritiesRepository authoritiesRepository;

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

        if (getAuth().isAuthenticated()) {
            Authorities auth = authoritiesRepository.findByUsername(getAuth().getName());

            if (auth == null) {
                return "login";
            }
            if (auth.getAuthority().equals("ROLE_ADMIN")) {
                return "redirect:/admin";
            } else if (auth.getAuthority().equals("ROLE_USER")) {
                return "redirect:/cars";
            }
        }
        return "login";
    }

    private Authentication getAuth() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
