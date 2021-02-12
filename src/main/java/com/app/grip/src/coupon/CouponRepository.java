package com.app.grip.src.coupon;

import com.app.grip.src.coupon.models.Coupon;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CouponRepository extends CrudRepository<Coupon, Long> {
    List<Coupon> findByStatus(String status);
}
