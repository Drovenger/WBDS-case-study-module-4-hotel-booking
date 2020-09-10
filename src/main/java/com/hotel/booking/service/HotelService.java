package com.hotel.booking.service;

import com.hotel.booking.model.Hotel;
import com.hotel.booking.model.Manager;

public interface HotelService {
    Iterable<Hotel> findAll();

    Hotel findById(Integer id);

    void save(Hotel hotel);

    void remove(Hotel hotel);
}
