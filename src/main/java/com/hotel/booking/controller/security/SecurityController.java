package com.hotel.booking.controller.security;

import com.hotel.booking.model.User;
import com.hotel.booking.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class SecurityController {

    @GetMapping(value = {"/"})
    public String Homepage(Model model){
        return "views/index";
    }

    @RequestMapping("/home")
    public String home(){
        return "views/user/index";
    }

    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        return "error/404";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
        return "account/login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register(ModelMap model) {
        ModelAndView modelAndView = new ModelAndView("account/register");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @Autowired
    UserService userService;

    @PostMapping(value = "/submit")
    public ModelAndView submit(@Validated @Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        new User().validate(user, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("account/register");
            return modelAndView;
        }
        userService.save(user);
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        return modelAndView;
    }


}
