package com.hotel.booking.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bill")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String message;

    private String discount;

    @NotEmpty
    private String payment;

    @NotEmpty
    private Date dateOfPayment;

    @NotEmpty
    private Integer status;

    public Bill(Integer id, @NotEmpty User user, String message, String discount, @NotEmpty String payment, @NotEmpty Date dateOfPayment, @NotEmpty Integer status) {
        this.id = id;
        this.user = user;
        this.message = message;
        this.discount = discount;
        this.payment = payment;
        this.dateOfPayment = dateOfPayment;
        this.status = status;
    }

    public Bill(@NotEmpty User user, String message, String discount, @NotEmpty String payment, @NotEmpty Date dateOfPayment, @NotEmpty Integer status) {
        this.user = user;
        this.message = message;
        this.discount = discount;
        this.payment = payment;
        this.dateOfPayment = dateOfPayment;
        this.status = status;
    }

    public Bill() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public Date getDateOfPayment() {
        return dateOfPayment;
    }

    public void setDateOfPayment(Date dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
