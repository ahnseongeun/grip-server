package com.app.grip.src.review;

import com.app.grip.src.review.models.Review;
import com.app.grip.src.store.models.Store;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
    //List<Review> findAll(Sort sort);
    //List<Review> findByStoreOrderByCreateDateDesc(Store store);
}