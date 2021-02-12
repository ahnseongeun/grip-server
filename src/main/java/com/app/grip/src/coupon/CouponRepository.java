package com.app.grip.src.coupon;

import com.app.grip.src.coupon.models.Coupon;
import com.app.grip.src.user.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CouponRepository extends CrudRepository<Coupon, Long> {
    List<Coupon> findByStatus(String status);
    List<Coupon> findByStatusAndUser(String status, User user);
}
