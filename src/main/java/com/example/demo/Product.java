package com.example.demo;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "product")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Poduct_ID")
    private Long id;
    private String name;
    private int price;
    private String description;

    private Date last_update;

    @ManyToOne()
    @JoinColumn(referencedColumnName = "Category_ID")
    private Category category;


    public Product() {
    }

    @OneToMany(mappedBy="product")
    private Set<Ordered_product> Ordered_product;



    public Set<Ordered_product> getOrdered_product() {
        return Ordered_product;
    }

    public void setOrdered_product(Set<Ordered_product> ordered_product) {
        Ordered_product = ordered_product;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", price=" + price + ", description=" + description
                + ", last_update=" + last_update + "]";
    }

    public void setId(Long id) {
        this.id = id;
    }
}