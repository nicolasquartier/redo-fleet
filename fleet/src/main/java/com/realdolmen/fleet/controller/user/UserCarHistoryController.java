package com.realdolmen.fleet.controller.user;

import com.realdolmen.fleet.domain.User;
import com.realdolmen.fleet.domain.UserCarHistory;
import com.realdolmen.fleet.repository.UserCarHistoryRepository;
import com.realdolmen.fleet.service.impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;

@Controller
@RequestMapping("/user/")
public class UserCarHistoryController {

    @Autowired
    private AuthServiceImpl authService;

    @Autowired
    private UserCarHistoryRepository userCarHistoryRepository;

    @RequestMapping(value = "/car", method = RequestMethod.GET)
    public String getCurrentCompanyCar(Model model) {
        User currentUser = authService.getCurrentUser();
        UserCarHistory carhistory = userCarHistoryRepository.findByUserAndEndDateAfter(currentUser, LocalDate.now());

        if (carhistory != null) {
            model.addAttribute("carHistoryObj", carhistory);
        } else {
            model.addAttribute("noCarFoundError", "There are no cars linked to this user.");
        }

        return "user/car";
    }
}
