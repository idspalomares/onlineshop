package com.app.rccl.shop.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Purchase extends AbstractEntity {

    @OneToOne
    private Product product;

    private Integer quantity;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
