package com.example.demo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

public class Main {
    public static void main(String[] args){
        EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("db");
        Product product=new Product();
product.setDescription("desc");
product.setLast_update(new Date());
product.setName("product name");
product.setPrice(20);

    EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

    entityManager.persist(product);
    System.out.println(product);
    entityManager.getTransaction().commit();
    entityManagerFactory.close();


    }
}
