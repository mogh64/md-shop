package com.example.mdshop.repository;

import com.example.mdshop.model.ProductItem;
import com.example.mdshop.repository.projection.ProductItemClosed;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface ProductItemRepository extends CrudRepository<ProductItem,Integer> {
     Iterable<ProductItem> findByIsSoldFalseAndProductCatalogProductCategoryTitleContaining(String title);
     @Query("select p from ProductItem p inner join p.productCatalog pc  where pc.id =?1 and pc.brand=?2")
     List<ProductItem> findByProductCategoryAndBrand(int categoryId,String brand);
     @Query("select p from ProductItem p inner join p.productCatalog pc  where pc.title =:catalogTitle and pc.brand=:brand")
     List<ProductItem> findByProductCategoryAndBrand(String catalogTitle,String brand);
     @Query("select p from ProductItem p inner join p.productCatalog pc  where pc.brand in :brands")
     List<ProductItem> findByBrandNames(String[] brands);

     List<ProductItem> findNotSoldByPrice(@Param("price")BigDecimal price);
     List<ProductItemClosed> findClosedByProductCatalogTitle(String title);
}
