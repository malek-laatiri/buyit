package com.example.demo;

import javax.persistence.*;

@Entity
@Table(name="customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Customer_ID")
    private int id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String city_region;
    private String cc_number;

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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
    public String getCity_region() {
        return city_region;
    }
    public void setCity_region(String city_region) {
        this.city_region = city_region;
    }
    public String getCc_number() {
        return cc_number;
    }
    public void setCc_number(String cc_number) {
        this.cc_number = cc_number;
    }


}