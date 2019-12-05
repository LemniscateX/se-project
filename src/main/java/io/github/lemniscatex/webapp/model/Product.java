package io.github.lemniscatex.webapp.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
//    @GeneratedValue(strategy = GenerationType.AUTO)
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


    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
