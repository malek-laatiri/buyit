package com.example.demo;

import javax.persistence.*;

@Entity
@Table(name="Ordered_product")
public class Ordered_product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Ordered_product_ID")
    private Long id;
    private int quantity;


    @ManyToOne
    @JoinColumn(name="product_id", nullable=false)
    private Product product;


    @ManyToOne
    @JoinColumn(name="Customer_order_id", nullable=false)
    private Customer_order customer_order;




    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer_order getCustomer_order() {
        return customer_order;
    }

    public void setCustomer_order(Customer_order customer_order) {
        this.customer_order = customer_order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }



    public Long getId() {
        return id;
    }

}