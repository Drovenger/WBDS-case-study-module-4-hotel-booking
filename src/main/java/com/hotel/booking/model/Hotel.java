package com.hotel.booking.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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

    @OneToMany(mappedBy = "hotel_id")
    private List<Room> room;


    private String img;

    public Hotel(Integer id, @NotEmpty @Size(min = 8, max = 64) String name, @NotEmpty String description, @NotEmpty Integer status, String img, List<Room> room) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.img = img;
        this.room = room;
    }

    public Hotel(Integer id, @NotEmpty @Size(min = 8, max = 64) String name, @NotEmpty String description, @NotEmpty Integer status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public Hotel(@NotEmpty @Size(min = 8, max = 64) String name, @NotEmpty String description, @NotEmpty Integer status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public Hotel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<Room> getRoom() {
        return room;
    }

    public void setRoom(List<Room> room) {
        this.room = room;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}