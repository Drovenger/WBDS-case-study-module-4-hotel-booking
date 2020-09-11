package com.hotel.booking.service.manager;

import com.hotel.booking.model.Manager;
import com.hotel.booking.repository.ManagerRepository;
import com.hotel.booking.service.manager.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;

public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public Iterable<Manager> findById(Integer id) {
        return null;
    }

    @Override
    public void edit(Manager manager) {

    }

    @Override
    public void remove(Integer id) {

    }
}
