package com.hotel.booking.controller.admin;

import com.hotel.booking.model.Hotel;
import com.hotel.booking.model.Room;
import com.hotel.booking.model.User;
import com.hotel.booking.repository.HotelRepository;
import com.hotel.booking.repository.RoomRepository;
import com.hotel.booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private RoomRepository roomRepository;

    @GetMapping
    public String adminPage(ModelMap model) {
        return "views/admin/widgets";
    }

    @RequestMapping(value = "/list/user", method = RequestMethod.GET)
    public ModelAndView listUser(){
        ModelAndView modelAndView = new ModelAndView("views/admin/");
        modelAndView.addObject("listUser", userRepository.findAll());
        return modelAndView;
    }

        @RequestMapping(value = "/add/user", method = RequestMethod.GET)
    public ModelAndView addUser(){
        ModelAndView modelAndView = new ModelAndView("views/admin/");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @RequestMapping(value = "/add/user", method = RequestMethod.POST)
    public ModelAndView addUsers(@ModelAttribute("user") User user){
        ModelAndView modelAndView = new ModelAndView("views/admin/");
        userRepository.save(user);
        return modelAndView;
    }

    @RequestMapping(value = "/edit/user/{id}", method = RequestMethod.GET)
    public ModelAndView editUser(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView("views/admin/");
        modelAndView.addObject("user", userRepository.findOne(id));
        return modelAndView;
    }

    @RequestMapping(value = "/edit/user", method = RequestMethod.PUT)
    public ModelAndView editUsers(@ModelAttribute("user") User user){
        ModelAndView modelAndView = new ModelAndView("views/admin/");
        userRepository.save(user);
        return modelAndView;
    }

    @RequestMapping(value = "/delete/user/{id}", method = RequestMethod.DELETE)
    public ModelAndView deleteUser(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView("views/admin/");
        userRepository.delete(id);
        return modelAndView;
    }

    @RequestMapping(value = "/list/hotel", method = RequestMethod.GET)
    public ModelAndView listHotel(){
        ModelAndView modelAndView = new ModelAndView("views/admin/");
        modelAndView.addObject("listHotel", hotelRepository.findAll());
        return modelAndView;
    }

    @RequestMapping(value = "/add/hotel", method = RequestMethod.GET)
    public ModelAndView addHotel(){
        ModelAndView modelAndView = new ModelAndView("views/admin/");
        modelAndView.addObject("listHotel", hotelRepository.findAll());
        return modelAndView;
    }

    @RequestMapping(value = "/add/hotel", method = RequestMethod.POST)
    public ModelAndView addHotels(@ModelAttribute("hotel")Hotel hotel){
        ModelAndView modelAndView = new ModelAndView("views/admin/");
        hotelRepository.save(hotel);
        return modelAndView;
    }

    @RequestMapping(value = "/edit/hotel/{id}", method = RequestMethod.GET)
    public ModelAndView editHotel(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView("views/admin/");
        modelAndView.addObject("hotel", hotelRepository.findOne(id));
        return modelAndView;
    }

    @RequestMapping(value = "/edit/hotel", method = RequestMethod.PUT)
    public ModelAndView editHotels(@ModelAttribute("hotel") Hotel hotel){
        ModelAndView modelAndView = new ModelAndView("views/admin/");
        hotelRepository.save(hotel);
        return modelAndView;
    }

    @RequestMapping(value = "/delete/hotel/{id}", method = RequestMethod.DELETE)
    public ModelAndView deleteHotel(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView("views/admin/");
        hotelRepository.delete(id);
        return modelAndView;
    }

    @RequestMapping(value = "/list/room", method = RequestMethod.GET)
    public ModelAndView listRoom(){
        ModelAndView modelAndView = new ModelAndView("views/admin/");
        modelAndView.addObject("listRoom", roomRepository.findAll());
        return modelAndView;
    }

    @RequestMapping(value = "/add/room", method = RequestMethod.GET)
    public ModelAndView addRoom(){
        ModelAndView modelAndView = new ModelAndView("views/admin/");
        modelAndView.addObject("listRoom", roomRepository.findAll());
        return modelAndView;
    }

    @RequestMapping(value = "/edit/room/{id}", method = RequestMethod.GET)
    public ModelAndView editRoom(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView("views/admin/");
        modelAndView.addObject("room", roomRepository.findOne(id));
        return modelAndView;
    }

    @RequestMapping(value = "/edit/room", method = RequestMethod.GET)
    public ModelAndView editRooms(@ModelAttribute("room")Room room){
        ModelAndView modelAndView = new ModelAndView("views/admin/");
        roomRepository.save(room);
        return modelAndView;
    }

    @RequestMapping(value = "/delete/room/{id}", method = RequestMethod.GET)
    public ModelAndView deleteRoom(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView("views/admin/");
        roomRepository.delete(id);
        return modelAndView;
    }

    @RequestMapping(value = "/map", method = RequestMethod.GET)
    public ModelAndView map(){
        ModelAndView modelAndView = new ModelAndView("views/admin/google-map");
        return modelAndView;
    }
}
