package com.realdolmen.fleet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    // FIXME: 11/04/2016 Should be replaced by index page extending the layout with static content
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "login";
    }

    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public String cars() {
        return "carcatalog";
    }

    @RequestMapping(value = "/cardetail", method = RequestMethod.GET)
    public String cardetail() {
        return "cardetail";
    }

    @RequestMapping(value = "/mycar", method = RequestMethod.GET)
    public String mycar() {
        return "mycar";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin() {
        return "/admin/admin";
    }


}
