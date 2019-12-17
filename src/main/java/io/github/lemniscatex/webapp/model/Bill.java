package io.github.lemniscatex.webapp.model;

import javax.persistence.*;

@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    @OneToOne
    public Product product;
    public Double productPrice;
    public Integer productCount;
    public Double totalPrice;
    public String status;
    public Integer userId;
    public String receiverName;
    public String receiverTel;
    public String receiverAddress;

    public Bill() {
    }

    public Bill(Product product, Double productPrice, Integer productCount, Double totalPrice, String status, Integer userId, String receiverName, String receiverTel, String receiverAddress) {
        this.product = product;
        this.productPrice = productPrice;
        this.productCount = productCount;
        this.totalPrice = totalPrice;
        this.status = status;
        this.userId = userId;
        this.receiverName = receiverName;
        this.receiverTel = receiverTel;
        this.receiverAddress = receiverAddress;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
