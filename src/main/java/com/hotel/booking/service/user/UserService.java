package com.hotel.booking.service.user;

import com.hotel.booking.model.User;
import com.hotel.booking.service.Service;

import java.util.List;

public interface UserService {
    List<User> findAll();


    User findById(Long id);

    User findByUsername(String userName);

     void save(User user) ;



    void remove(Long id);

}
