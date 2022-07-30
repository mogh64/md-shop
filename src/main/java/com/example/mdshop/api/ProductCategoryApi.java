package com.example.mdshop.api;

import com.example.mdshop.model.ProductCategory;
import com.example.mdshop.repository.ProductCategoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product-category")
public class ProductCategoryApi {
    private ProductCategoryRepository repository;

    public ProductCategoryApi(ProductCategoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Iterable<ProductCategory> getAll() {
        return repository.findAll();
    }
}
