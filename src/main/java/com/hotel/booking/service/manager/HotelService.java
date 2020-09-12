package com.hotel.booking.service.manager;

import com.hotel.booking.model.Hotel;

public interface HotelService {
    Iterable<Hotel> findAll();

    Hotel findById(Integer id);

    void save(Hotel hotel);

    void remove(Hotel hotel);
}
