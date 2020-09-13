package com.hotel.booking.repository;

import com.hotel.booking.model.Room;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository<Room, Long> {
}
