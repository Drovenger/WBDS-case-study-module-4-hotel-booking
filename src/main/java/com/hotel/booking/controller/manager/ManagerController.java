package com.hotel.booking.controller.manager;

import com.hotel.booking.model.Hotel;
import com.hotel.booking.service.manager.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/management")
public class ManagerController {

    @Autowired
    private HotelService hotelService;

    @GetMapping(value = "/home")
    public String dbaPage(ModelMap model) {
        return "views/user/blog";
    }
}
