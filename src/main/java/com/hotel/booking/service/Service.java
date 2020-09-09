package com.hotel.booking.service;

public interface Service<T> {
    Iterable<T> listFindByName (String name);
}
