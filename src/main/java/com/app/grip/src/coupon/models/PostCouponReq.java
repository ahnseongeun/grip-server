package com.app.grip.src.coupon.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class PostCouponReq {
    private String content;
    private Integer discount;
    private Integer minimumPrice;
    private String effectiveDate;
}