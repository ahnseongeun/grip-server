package com.app.grip.src.review.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetReviewRes {
    private final Long reviewId;
    private final Long storeId;
    private final Long userNo;
    private final Integer star;
    private final String content;
    private final List<PictureRes> picture;
}