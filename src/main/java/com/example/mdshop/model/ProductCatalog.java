package com.example.mdshop.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class ProductCatalog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String brand;
    @OneToMany(mappedBy = "productCatalog", cascade = CascadeType.ALL)
    private Set<ProductItem> productItems;
    @ManyToOne
    private ProductCategory productCategory;

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }



    protected ProductCatalog() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }


    public ProductCatalog(int id, String title, String brand) {
        this.id = id;
        this.title = title;
        this.brand = brand;
    }
}
