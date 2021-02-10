package com.app.grip.src.review;

import com.app.grip.src.review.models.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
    List<Review> findByStatus(String status);
    List<Review> findByStatusAndId(String status, Long reviewNo);
}
