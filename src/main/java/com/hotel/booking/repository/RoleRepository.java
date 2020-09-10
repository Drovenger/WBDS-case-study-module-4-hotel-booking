package com.hotel.booking.repository;

import com.hotel.booking.model.Role;

interface RoleService {
    Iterable<Role> findAll();

    Role findById(Long id);

    void save(Role role);

    void remove(Long id);
}
