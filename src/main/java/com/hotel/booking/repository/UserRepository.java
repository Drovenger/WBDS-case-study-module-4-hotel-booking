package com.hotel.booking.repository;

import com.hotel.booking.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername (String name);
}
