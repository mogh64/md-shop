package com.example.mdshop.api;

import com.example.mdshop.dto.ProductAddDto;
import com.example.mdshop.dto.ProductFindByTitleDto;
import com.example.mdshop.model.ProductCatalog;
import com.example.mdshop.model.ProductItem;
import com.example.mdshop.repository.ProductCatalogRepository;
import com.example.mdshop.repository.ProductItemRepository;
import com.example.mdshop.repository.projection.ProductItemClosed;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductApi {
    private ProductItemRepository productItemRepository;
    private ProductCatalogRepository productCatalogRepository;

    public ProductApi(ProductItemRepository productItemRepository, ProductCatalogRepository productCatalogRepository) {
        this.productItemRepository = productItemRepository;
        this.productCatalogRepository = productCatalogRepository;
    }
    @PostMapping("/product-item")
    public ResponseEntity<Integer> AddProductItem(@RequestBody ProductAddDto productAddDto){
       var productCatalogResult = productCatalogRepository.findById(productAddDto.getProductCatalogId());
       if(productCatalogResult.isEmpty()){
           return ResponseEntity.notFound().build();
       }
       if(productAddDto.getCount()<=0){
           return ResponseEntity.ok(0);
       }
        List<ProductItem> productItemList = new ArrayList<>();
        for(int i=0;i<productAddDto.getCount();i++){
            var newItem = new ProductItem(productAddDto.getPrice(),false,new BigDecimal(0), UUID.randomUUID());
            newItem.setProductCatalog(productCatalogResult.get());
            productItemList.add(newItem);
        }
        productItemRepository.saveAll(productItemList);
        return ResponseEntity.ok(productItemList.size());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Iterable<ProductItem>> getAvailableProductItems(@PathVariable int id){
        return  null;
    }
    @GetMapping("/search")
    public ResponseEntity<Iterable<ProductCatalog>> search(@RequestParam(name = "title")String title){
        return ResponseEntity.ok(productCatalogRepository.findByTitleContaining(title));
    }
    @GetMapping("/search/not-sold")
    public ResponseEntity<Iterable<ProductItem>> searchAvailableItems(@RequestParam(name = "cat",required = true)String catTitle){
        return ResponseEntity.ok(productItemRepository.findByIsSoldFalseAndProductCatalogProductCategoryTitleContaining(catTitle));
    }
    @GetMapping("/search/by-cat")
    public ResponseEntity<List<ProductItem>> searchByProductCategoryAndBrand(@RequestParam(name = "cat",required = true)Integer catId,
                                                                             @RequestParam(name = "brand",required = true)String brand){
        return ResponseEntity.ok(productItemRepository.findByProductCategoryAndBrand(catId,brand));
    }
    @GetMapping("/search/by-title")
    public ResponseEntity<List<ProductItem>> searchByProductCategoryTitleAndBrand(@RequestParam(name = "title",required = true)String title,
                                                                             @RequestParam(name = "brand",required = true)String brand){
        return ResponseEntity.ok(productItemRepository.findByProductCategoryAndBrand(title,brand));
    }
    @GetMapping("/search/by-brands")
    public ResponseEntity<List<ProductItem>> searchByBrands(@RequestParam(name = "brands",required = true)String brands){
        var brandNames =  brands.split(",");
        return ResponseEntity.ok(productItemRepository.findByBrandNames(brandNames));
    }
    @GetMapping("/search/by-price")
    public ResponseEntity<List<ProductItem>> searchByPrice(@RequestParam(name = "price",required = true)BigDecimal price){

        return ResponseEntity.ok(productItemRepository.findNotSoldByPrice(price));
    }
    @GetMapping("/detail/by-name")
    public ResponseEntity<List<ProductItemClosed>> findDetailByQrCode(@RequestBody ProductFindByTitleDto findDto){

        return ResponseEntity.ok(productItemRepository.findClosedByProductCatalogTitle(findDto.title));
    }
}
