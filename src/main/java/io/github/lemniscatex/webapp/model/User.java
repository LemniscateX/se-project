package io.github.lemniscatex.webapp.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public Integer id;
    public String name;
    public String email;
    public String filePath;

    public User() {
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
