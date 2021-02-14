package com.app.grip.src.product;

import com.app.grip.src.product.models.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findByStatusOrStatusOrderByCreateDateDesc(String statusY, String statusC);
}