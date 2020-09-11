package com.hotel.booking.controller.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
//@SessionAttributes("bill") //tim session ten bill
public class SecurityController {
    //gan session khi khong tim thay
//    @ModelAttribute("bill")
//    public Bill getBill () {
//        return new Bill();
//    }

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


    @GetMapping(value = {"/"})
    public String Homepage(Model model, HttpSession session){
        //set time die session
        //session.setMaxInactiveInterval(100);
        
        model.addAttribute("user", getPrincipal());
        return "account/register";
    }

    @RequestMapping("/home")
    public String home(){
        return "views/admin/widgets";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "error/404";
    }

    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "error/500";
    }

    @RequestMapping(value = "/dba", method = RequestMethod.GET)
    public String dbaPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "erroe/404";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
        return "account/login";
    }

    @RequestMapping("/logout")
    public String logout(){
        return "account/lock";
    }

}
