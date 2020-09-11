package com.hotel.booking.service;

import com.hotel.booking.model.Manager;

public interface Service<T> {

    Iterable<T> findById(Integer id);

    void edit(Manager manager);

    void remove(Integer id);
}
