package com.app.grip.src.product.models;

import com.app.grip.src.store.models.GetStoresRes;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetProductsRes {
    private final Long productId;
    private final String name;
    private final String content;
    private final Integer price;
    private final String pictureURL;
    private final GetStoresRes store;
    private final String productCategoryName;
    private final String createDate;
    private final String status;
}