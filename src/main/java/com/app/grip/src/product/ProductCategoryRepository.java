package com.app.grip.src.product;

import com.app.grip.src.product.models.ProductCategory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductCategoryRepository extends CrudRepository<ProductCategory, Long> {
    List<ProductCategory> findByStatusOrderByCreateDateDesc(String status);
    List<ProductCategory> findByStatusAndName(String status, String name);
}