package com.hotel.booking.model;

import org.springframework.validation.BindingResult;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NotEmpty
    private String first_name;

    @Column(nullable = false)
    @NotEmpty
    private String last_name;


    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    @Size(min = 9, max = 11)
    private String phone;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @Size(min = 4, max = 32)
    private String username;

    @Column(nullable = false)
    @Size(min = 6, max = 32)
    private String password;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;

    private String img;

    public User() {
    }

    public User(@NotEmpty String first_name, @NotEmpty String last_name, int age, @Size(min = 9, max = 11) String phone, String address, String email, @Size(min = 4, max = 32) String username, @Size(min = 6, max = 32) String password) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.age=age;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User(Integer id, @NotEmpty String first_name, @NotEmpty String last_name, int age, @Size(min = 9, max = 11) String phone, String address, String email, @Size(min = 4, max = 32) String username, @Size(min = 6, max = 32) String password, List<Role> roles) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.age=age;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.img = img;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }



    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void validate(User user, BindingResult bindingResult) {
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public User(Integer id, @NotEmpty String first_name, @NotEmpty String last_name, @NotEmpty Integer age, @Size(min = 9, max = 11) String phone, String address, String email, @Size(min = 4, max = 32) String username, @Size(min = 6, max = 32) String password, List<Role> roles, String img) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.img = img;
    }
}
