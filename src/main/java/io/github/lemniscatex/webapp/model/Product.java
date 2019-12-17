package io.github.lemniscatex.webapp.model;

import javax.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;
    public String name;
    public Double price;
    public Integer quantity;
    public String description;
    public String category;
    public String photosPath;

    public Product() {
    }

    public Product(String name, Double price, Integer quantity, String description, String category) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.category = category;
    }
}
