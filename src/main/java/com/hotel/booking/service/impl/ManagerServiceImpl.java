package com.hotel.booking.service.impl;

import com.hotel.booking.model.Manager;
import com.hotel.booking.repository.ManagerRepository;
import com.hotel.booking.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;

public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public Iterable<Manager> findAll() {
        return managerRepository.findAll();
    }

    @Override
    public Manager findById(Integer id) {
        return managerRepository.findOne(id);
    }

    @Override
    public void save(Manager manager) {
        managerRepository.save(manager);
    }

    @Override
    public void remove(Integer id) {
        managerRepository.delete(id);
    }
}
