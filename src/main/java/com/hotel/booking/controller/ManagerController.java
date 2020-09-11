package com.hotel.booking.controller;

import com.hotel.booking.model.Hotel;
import com.hotel.booking.model.Manager;
import com.hotel.booking.service.manager.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/management")
public class ManagerController {

    @Autowired
    private HotelService hotelService;

    @GetMapping
    public ModelAndView list(@ModelAttribute("manager") Manager manager) {
        Iterable<Hotel> hotels = hotelService.findAllByManager(manager);
        ModelAndView modelAndView = new ModelAndView("management/");

        return modelAndView;
    }
}
