package com.example.mdshop.model;

import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@NamedQuery(name = "ProductItem.findNotSoldByPrice",query = "select p from ProductItem p where p.isSold=false" +
        " and p.price<=:price")
@Entity
public class ProductItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private BigDecimal price;
    private boolean isSold;
    private BigDecimal discount;
    private UUID qrCode;

    public ProductCatalog getProductCatalog() {
        return productCatalog;
    }

    public void setProductCatalog(ProductCatalog productCatalog) {
        this.productCatalog = productCatalog;
    }

    @ManyToOne
    private ProductCatalog productCatalog;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductItem that = (ProductItem) o;
        return Objects.equals(qrCode, that.qrCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(qrCode);
    }

    public UUID getQrCode() {
        return qrCode;
    }

    public void setQrCode(UUID qrCode) {
        this.qrCode = qrCode;
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

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public ProductItem(BigDecimal price, boolean isSold, BigDecimal discount,UUID qrCode) {
        this.price = price;
        this.isSold = isSold;
        this.discount = discount;
        this.qrCode = qrCode;
    }

    protected ProductItem() {

    }
}
