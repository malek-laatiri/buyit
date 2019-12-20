package com.example.demo;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="customer_order")
public class Customer_order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_order_ID")
    private Long id;
    private int amount;
    private int confirmation_number;
    private Date date_created;

    @ManyToOne
    @JoinColumn(referencedColumnName="Customer_ID")
    private Customer customer;
    @OneToMany(mappedBy="customer_order")
    private Set<Ordered_product> ordered_product;

    public Set<Ordered_product> getOrdered_product() {
        return ordered_product;
    }

    public void setOrdered_product(Set<Ordered_product> ordered_product) {
        this.ordered_product = ordered_product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getConfirmation_number() {
        return confirmation_number;
    }
    public void setConfirmation_number(int confirmation_number) {
        this.confirmation_number = confirmation_number;
    }
    public Date getDate_created() {
        return date_created;
    }
    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

}