package com.app.grip.src.product;

import com.app.grip.src.product.models.ProductCategory;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductCategoryRepository extends CrudRepository<ProductCategory, Long> {
    List<ProductCategory> findAll(Sort sort);
    List<ProductCategory> findByStatusAndName(String status, String name);
}