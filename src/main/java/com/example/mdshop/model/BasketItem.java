package com.example.mdshop.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class BasketItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private BigDecimal price;
    @OneToOne(optional = false)
    private ProductItem productItem;

    @ManyToOne
    private Basket order;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasketItem orderItem = (BasketItem) o;
        return Objects.equals(productItem, orderItem.productItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productItem);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ProductItem getProductItem() {
        return productItem;
    }

    public void setProductItem(ProductItem productItem) {
        this.productItem = productItem;
    }

    public BasketItem(BigDecimal price, ProductItem productItem) {
        this.price = price;
        this.productItem = productItem;
    }

    protected BasketItem() {

    }

}
