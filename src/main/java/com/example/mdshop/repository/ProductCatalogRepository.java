package com.example.mdshop.repository;

import com.example.mdshop.model.ProductCatalog;
import org.springframework.data.repository.CrudRepository;

public interface ProductCatalogRepository extends CrudRepository<ProductCatalog,Integer> {
    Iterable<ProductCatalog>findByTitleContaining(String title);
}
