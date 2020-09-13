package com.hotel.booking.controller.security;

import com.hotel.booking.model.Bill;
import com.hotel.booking.model.User;
import com.hotel.booking.repository.UserRepository;
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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@SessionAttributes("bill")
public class SecurityController {
    @Autowired
    private UserRepository userRepository;

    @ModelAttribute
    private Bill bill() {
        return new Bill();
    }

    private User getUser() {
        String userName = null;
        User user = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
            user = userRepository.findByUsername(userName);
        }
        return user;
    }

    @GetMapping(value = {"/"})
    public String Homepage(@ModelAttribute("bill") Bill bill) {
        if (bill.getUser() == null) {
            bill.setUser(getUser());
        }
        return "views/index";
    }

    @RequestMapping("/home")
    public String home() {
        return "views/user/index";
    }

    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        return "error/404";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(@CookieValue(value = "saveUser", defaultValue = "") String saveUserName,
            Model model, HttpServletResponse response, HttpServletRequest request) {
        Cookie cookie = new Cookie("setUser", saveUserName);
        cookie.setMaxAge(24 * 60 * 60);
        response.addCookie(cookie);
        Cookie[] cookies = request.getCookies();
        for (Cookie ck : cookies) {
            if (ck.getName().equals("saveUser")) {
                model.addAttribute("cookieValue", ck);
                break;
            } else {
                ck.setValue("");
                model.addAttribute("cookieValue", ck);
                break;
            }
        }
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
