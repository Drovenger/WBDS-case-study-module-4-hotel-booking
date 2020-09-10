package com.hotel.booking.service;

import com.hotel.booking.model.Manager;

public interface ManagerService {
    Iterable<Manager> findAll();

    Manager findById(Integer id);

    void save(Manager manager);

    void remove(Integer id);
}
