package com.hotel.booking.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public ModelAndView T(){
//        ModelAndView modelAndView = new ModelAndView();
//        return modelAndView;
//    }
    @GetMapping
    public String adminPage(ModelMap model) {
        return "views/admin/widgets";
    }

}
