package com.app.grip.src.product.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class PostProductReq {
    private String name;
    private String content;
    private Integer price;
    private String productCategoryName;
    private String pictureURL;
}