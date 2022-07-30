package com.example.mdshop.dto;

import java.math.BigDecimal;

public class ProductAddDto {
    private int productCatalogId;
    private int count;
    private BigDecimal price;
    public ProductAddDto(){}

    public int getProductCatalogId() {
        return productCatalogId;
    }

    public void setProductCatalogId(int productCatalogId) {
        this.productCatalogId = productCatalogId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
