package com.realdolmen.fleet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    // FIXME: 11/04/2016 Should be replaced by index page extending the layout with static content
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "layout";
    }

}
