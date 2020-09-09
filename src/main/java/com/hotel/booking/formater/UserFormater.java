package com.hotel.booking.formater;

import com.hotel.booking.model.User;
import com.hotel.booking.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.util.Locale;

public class UserFormater implements Formatter<User> {
    private UserService userService;

    @Autowired
    public UserFormater(UserService userService) {
        this.userService = userService;
    }

    @Override
    public User parse(String text, Locale locale) {
        return userService.findById(Long.parseLong(text));
    }

    @Override
    public String print(User object, Locale locale) {
        return "[" + object.getId() + ", " +object.getUsername() + "]";
    }
}
