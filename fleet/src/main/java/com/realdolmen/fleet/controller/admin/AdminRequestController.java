package com.realdolmen.fleet.controller.admin;

import com.realdolmen.fleet.domain.User;
import com.realdolmen.fleet.domain.UserCarHistory;
import com.realdolmen.fleet.repository.CompanyCarRepository;
import com.realdolmen.fleet.repository.UserCarHistoryRepository;
import com.realdolmen.fleet.service.impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;
import java.time.temporal.TemporalAmount;
import java.util.List;

@RequestMapping("/admin")
@Controller
public class AdminRequestController {

    @Autowired
    private AuthServiceImpl authService;

    @Autowired
    private CompanyCarRepository companyCarRepository;

    @Autowired
    private UserCarHistoryRepository userCarHistoryRepository;

    @RequestMapping(value = "/requests", method = RequestMethod.GET)
    public String requestIndex(Model model) {
        List<UserCarHistory> userCarHistories = userCarHistoryRepository.findAllByCompanyCarApprovedFalse();
        model.addAttribute("userCarHistories", userCarHistories);
        return "admin/requests";
    }

    @RequestMapping(value = "/requests/approve/{id}", method = RequestMethod.GET)
    public String approveARequest(Model model, @PathVariable("id") Long id) {
        //remove current car
        UserCarHistory currentUserCarHistory = userCarHistoryRepository.findOne(id);
        User user = currentUserCarHistory.getUser();

        UserCarHistory userPreviousCar = userCarHistoryRepository.findByUserAndEndDateAfterAndCompanyCarApprovedTrueAndCompanyCarActiveTrue(user, LocalDate.now());
        if(userPreviousCar != null) {
            userPreviousCar.getCompanyCar().setActive(false);
            userPreviousCar.setEndDate(LocalDate.now().minusDays(1));
            userCarHistoryRepository.save(userPreviousCar);
        }

        //approve new car
        currentUserCarHistory.getCompanyCar().setApproved(true);
        userCarHistoryRepository.save(currentUserCarHistory);

        //get the rest of the non-approved cars
        List<UserCarHistory> userCarHistories = userCarHistoryRepository.findAllByCompanyCarApprovedFalse();
        model.addAttribute("userCarHistories", userCarHistories);
        return "admin/requests";
    }

    @RequestMapping(value = "/requests/decline/{id}", method = RequestMethod.GET)
    public String declineARequest(Model model, @PathVariable("id") Long id) {
        userCarHistoryRepository.delete(id);

        //get the rest of the non-approved cars
        List<UserCarHistory> userCarHistories = userCarHistoryRepository.findAllByCompanyCarApprovedFalse();
        model.addAttribute("userCarHistories", userCarHistories);
        return "admin/requests";
    }

    @RequestMapping(value = "/companyCars", method = RequestMethod.GET)
    public String companyCarsIndex(Model model) {
        List<UserCarHistory> userCarHistories = userCarHistoryRepository.findAllByCompanyCarApprovedTrue();
        model.addAttribute("userCarHistories", userCarHistories);
        return "admin/companyCars";
    }


}
