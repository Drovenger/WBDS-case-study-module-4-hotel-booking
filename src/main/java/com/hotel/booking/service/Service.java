package com.hotel.booking.service;

public interface Service<T> {

    Iterable<T> findById(Integer id);

    void remove(Integer id);
}
