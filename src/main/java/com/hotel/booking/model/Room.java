package com.hotel.booking.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "hotel_room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @Column(nullable = false)
    @NotEmpty
    @Size(min = 8, max = 64)
    private String name;

    @Column(nullable = false)
    @NotEmpty
    private String description;

    @Column(nullable = false)
    @NotEmpty
    private Integer status;

    @Column(nullable = false)
    @NotEmpty
    private double price;

    public Room() {
    }

    public Room(Integer id, @NotEmpty Hotel hotel, @NotEmpty @Size(min = 8, max = 64) String name, @NotEmpty String description, @NotEmpty Integer status, @NotEmpty double price) {
        this.id = id;
        this.hotel = hotel;
        this.name = name;
        this.description = description;
        this.status = status;
        this.price = price;
    }

    public Room(@NotEmpty Hotel hotel, @NotEmpty @Size(min = 8, max = 64) String name, @NotEmpty String description, @NotEmpty Integer status, @NotEmpty double price) {
        this.hotel = hotel;
        this.name = name;
        this.description = description;
        this.status = status;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
