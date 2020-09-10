package com.hotel.booking.repository;

import com.hotel.booking.model.Manager;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ManagerRepository extends PagingAndSortingRepository<Manager, Integer> {
}
