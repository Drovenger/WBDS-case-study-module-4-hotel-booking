package com.hotel.booking.controller.security;

import com.hotel.booking.model.User;
import com.hotel.booking.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    // lay user name trong Authen ra
    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }


    @GetMapping(value = {"/", "/home"})
    public String Homepage(Model model){
        model.addAttribute("user", getPrincipal());
        return "admin/widgets";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "admin/404";
    }

    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "admin/500";
    }

    @RequestMapping(value = "/dba", method = RequestMethod.GET)
    public String dbaPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "admin/404";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
        return "account/login";
    }

    @RequestMapping("/logout")
    public String logout(){
        return "account/lock";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET )
    public ModelAndView register(ModelMap model) {
        ModelAndView modelAndView = new ModelAndView("account/register");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @Autowired
    UserService  userService;
    @PostMapping(value = "/submit")
    public ModelAndView submit(@Validated @Valid @ModelAttribute("user") User user, BindingResult bindingResult){
        new User().validate(user,bindingResult);
        if(bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("account/register");
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        userService.save(user);
        return modelAndView;
    }


}
