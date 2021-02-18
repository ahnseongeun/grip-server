package com.app.grip.src.product.models;

import com.app.grip.src.review.models.GetReviewRes;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetProductRes {
    private final Long productId;
    private final String name;
    private final String content;
    private final Integer price;
    private final String pictureURL;
    private final String storeName;
    private final String productCategoryName;
    private final String createDate;
    private final String status;
    private final Double avgStar;
    private final List<GetReviewRes> reviewList;
}