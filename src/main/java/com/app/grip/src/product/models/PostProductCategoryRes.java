package com.app.grip.src.product.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostProductCategoryRes {
    private final Long productsCategoryId;
    private final String name;
}