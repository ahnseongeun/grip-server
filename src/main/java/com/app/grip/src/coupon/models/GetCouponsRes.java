package com.app.grip.src.coupon.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetCouponsRes {
    private final Long couponId;
    private final Long userNo;
    private final String userName;
    private final String content;
    private final Integer discount;
    private final Integer minimumPrice;
    private final String effectiveDate;
    private final String createDate;
    private final String updateDate;
    private final String status;
}