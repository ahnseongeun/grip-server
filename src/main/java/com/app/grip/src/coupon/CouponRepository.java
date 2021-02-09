package com.app.grip.src.coupon;

import com.app.grip.src.coupon.models.Coupon;
import org.springframework.data.repository.CrudRepository;

public interface CouponRepository extends CrudRepository<Coupon, Long> {
}
