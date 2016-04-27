package com.realdolmen.fleet.controller.admin;

import com.realdolmen.fleet.domain.UserCarHistory;
import com.realdolmen.fleet.repository.CompanyCarRepository;
import com.realdolmen.fleet.repository.UserCarHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequestMapping("/admin")
@Controller
public class AdminRequestController {


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

    @RequestMapping(value = "/companyCars", method = RequestMethod.GET)
    public String companyCarsIndex(Model model) {
        List<UserCarHistory> userCarHistories = userCarHistoryRepository.findAllByCompanyCarApprovedTrue();
        model.addAttribute("userCarHistories", userCarHistories);
        return "admin/companyCars";
    }


}
