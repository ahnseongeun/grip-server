package com.app.grip.src.product.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostProductRes {
    private final Long productId;
    private final String name;
    private final String content;
    private final Integer price;
    private final String pictureURL;
    private final Long storeId;
    private final Long productCategoryId;
}