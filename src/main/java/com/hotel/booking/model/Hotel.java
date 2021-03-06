package com.hotel.booking.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotEmpty
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;

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

    private String img;

    public Hotel(Integer id, @NotEmpty Manager manager, @NotEmpty @Size(min = 8, max = 64) String name, @NotEmpty String description, @NotEmpty Integer status, String img) {
        this.id = id;
        this.manager = manager;
        this.name = name;
        this.description = description;
        this.status = status;
        this.img = img;
    }

    public Hotel(Integer id, @NotEmpty Manager manager, @NotEmpty @Size(min = 8, max = 64) String name, @NotEmpty String description, @NotEmpty Integer status) {
        this.id = id;
        this.manager = manager;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public Hotel(@NotEmpty Manager manager, @NotEmpty @Size(min = 8, max = 64) String name, @NotEmpty String description, @NotEmpty Integer status) {
        this.manager = manager;
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

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
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