package com.hotel.booking.repository.impl;

import com.hotel.booking.model.Role;
import com.hotel.booking.repository.RoleRepository;

public class RoleRepositoryImpl implements RoleRepository {
    @Override
    public <S extends Role> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Role> Iterable<S> save(Iterable<S> entities) {
        return null;
    }

    @Override
    public Role findOne(Long aLong) {
        return null;
    }

    @Override
    public boolean exists(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Role> findAll() {
        return null;
    }

    @Override
    public Iterable<Role> findAll(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void delete(Role entity) {

    }

    @Override
    public void delete(Iterable<? extends Role> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
