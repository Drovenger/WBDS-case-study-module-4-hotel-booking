package com.hotel.booking.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "management")
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer manager_id;

    @OneToMany(mappedBy = "manager")
    private Set<Hotel> hotels;

    public Manager() {
    }

    public Manager(Integer manager_id) {
        this.manager_id = manager_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getManager_id() {
        return manager_id;
    }

    public void setManager_id(Integer manager_id) {
        this.manager_id = manager_id;
    }

    public Set<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(Set<Hotel> hotels) {
        this.hotels = hotels;
    }
}
