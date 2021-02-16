package com.app.grip.src.review;

import com.app.grip.src.review.models.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
    List<Review> findAllByOrderByIdDesc();
    List<Review> findByStatusOrderByIdDesc(String status);
    List<Review> findByStatusAndProduct_ProductCategory_Name(String status, String categoryName);
}