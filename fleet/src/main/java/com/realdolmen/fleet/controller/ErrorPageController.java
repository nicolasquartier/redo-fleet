package com.realdolmen.fleet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorPageController {

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDeniedPage() {
        return "403";
    }


    @RequestMapping(value = {"/error", "/404"}, method = RequestMethod.GET)
    public String pageNotFound() {
        return "404";
    }
}

