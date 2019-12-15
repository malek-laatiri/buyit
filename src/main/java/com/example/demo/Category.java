package com.example.demo;

import javax.persistence.*;

@Entity
@Table(name="category")

public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Category_ID")
    private Long id;
    private String name;
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Category() {
        super();
    }

    public void setId(Long id) {
        this.id = id;
    }
}